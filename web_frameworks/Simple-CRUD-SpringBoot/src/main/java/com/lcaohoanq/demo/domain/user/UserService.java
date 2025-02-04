package com.lcaohoanq.demo.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public User create(UserDTO userDTO) {
        User user = User.builder()
            .username(userDTO.username())
            .password(userDTO.password())
            .build();
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(Long id, UserDTO userDTO) {
        User existingUser = findById(id);
        existingUser.setUsername(userDTO.username());
        existingUser.setPassword(userDTO.password());
        return userRepository.save(existingUser);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        User user = findById(id);
        userRepository.delete(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
}
