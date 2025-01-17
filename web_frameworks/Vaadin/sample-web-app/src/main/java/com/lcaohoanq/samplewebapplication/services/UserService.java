package com.lcaohoanq.samplewebapplication.services;

import com.lcaohoanq.samplewebapplication.components.LocalizationUtils;
import com.lcaohoanq.samplewebapplication.dtos.UpdateUserDTO;
import com.lcaohoanq.samplewebapplication.dtos.UserDTO;
import com.lcaohoanq.samplewebapplication.enums.UserStatus;
import com.lcaohoanq.samplewebapplication.exceptions.EmailAlreadyUsedException;
import com.lcaohoanq.samplewebapplication.exceptions.MalformBehaviourException;
import com.lcaohoanq.samplewebapplication.exceptions.PhoneAlreadyUsedException;
import com.lcaohoanq.samplewebapplication.exceptions.UpdateEmailException;
import com.lcaohoanq.samplewebapplication.exceptions.base.DataNotFoundException;
import com.lcaohoanq.samplewebapplication.models.User;
import com.lcaohoanq.samplewebapplication.repositories.UserRepository;
import com.lcaohoanq.samplewebapplication.utils.MessageKey;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements IUserService{

    private final UserRepository userRepository;
  private final LocalizationUtils localizationUtils;
  private final PasswordEncoder passwordEncoder;

  @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User login(String username, String password) {
        return userRepository.findByEmailAndPassword(username, password).orElseThrow(() -> new NullPointerException("User not found"));
    }

    @Override
    public User register(UserDTO userDTO) {
        if(userRepository.findByEmailAndPassword(userDTO.username(), userDTO.password()).isPresent()) {
            throw new IllegalArgumentException("User already exists");
        }
        return userRepository.save(User.builder()
                .email(userDTO.email())
                .password(userDTO.password())
                .build());
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public void softDelete(Long id) {
      User existingUser = findById(id);
      if(!existingUser.isActive()) throw new MalformBehaviourException("User is already deleted");
      userRepository.softDeleteUser(id);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

  @Transactional
  @Override
  public User update(Long userId, UpdateUserDTO updatedUserDTO) throws Exception {
    // Find the existing user by userId
    User existingUser = userRepository.findById(userId)
      .orElseThrow(() -> new DataNotFoundException(
        localizationUtils.getLocalizedMessage(MessageKey.USER_NOT_FOUND)
      ));

    // Check if the email is being changed and if it already exists for another user
    String newEmail = updatedUserDTO.email();

    if (newEmail != null && !newEmail.isEmpty()) {
      // Check if the new email is different from the current user's email
      if (!newEmail.equals(existingUser.getEmail())) {
        // Check if the new email is already in use by another user
        Optional<User> userWithNewEmail = userRepository.findByEmail(newEmail);

        if (userWithNewEmail.isPresent()) throw new EmailAlreadyUsedException("This email address is already registered");

        // If not, update the current user's email
        existingUser.setEmail(newEmail);
      }
      // If the email is the same as the current one, no changes are needed
    } else {
      throw new UpdateEmailException("This email cannot be empty");
    }

    // Check if the phone number is being changed and if it already exists for another user
    String newPhoneNumber = updatedUserDTO.phoneNumber();

    if (newPhoneNumber != null && !newPhoneNumber.isEmpty()) {
      // Check if the new phone number is different from the current user's phone number
      if (!newPhoneNumber.equals(existingUser.getPhoneNumber())) {
        // Check if the new phone number is already in use by another user
        Optional<User> userWithNewPhoneNumber = userRepository.findByPhoneNumber(newPhoneNumber);

        if (userWithNewPhoneNumber.isPresent()) throw new PhoneAlreadyUsedException("This phone number is already registered");

        // If not, update the current user's phone number
        existingUser.setPhoneNumber(newPhoneNumber);
      }
      // If the phone number is the same as the current one, no changes are needed
    } else {
      existingUser.setPhoneNumber(null);
    }

    // Update user information based on the DTO
    if (updatedUserDTO.firstName() != null) {
      existingUser.setFirstName(updatedUserDTO.firstName());
    }
    if (updatedUserDTO.lastName() != null) {
      existingUser.setLastName(updatedUserDTO.lastName());
    }
    if (updatedUserDTO.address() != null) {
      existingUser.setAddress(updatedUserDTO.address());
    }
    if (updatedUserDTO.status() != null) {
      existingUser.setStatus(UserStatus.valueOf(updatedUserDTO.status()));
    }
    if (updatedUserDTO.dob() != null) {
      existingUser.setDob(updatedUserDTO.dob());
    }
    if (updatedUserDTO.avatarUrl() != null) {
      existingUser.setAvatarUrl(updatedUserDTO.avatarUrl());
    }

    // Update the password if it is provided in the DTO
    if (updatedUserDTO.password() != null
      && !updatedUserDTO.password().isEmpty()) {
      if (!updatedUserDTO.password().equals(updatedUserDTO.confirmPassword())) {
        throw new DataNotFoundException("Password and confirm password must be the same");
      }
      String newPassword = updatedUserDTO.password();
      String encodedPassword = passwordEncoder.encode(newPassword);
      existingUser.setPassword(encodedPassword);
    }
    //existingUser.setRole(updatedRole);
    // Save the updated user
    return userRepository.save(existingUser);
  }
}
