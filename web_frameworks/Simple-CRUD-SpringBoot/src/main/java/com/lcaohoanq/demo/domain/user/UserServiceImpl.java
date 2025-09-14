package com.lcaohoanq.demo.domain.user;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User create(UserDTO userDTO) {
        if(userRepository.existsByEmail(userDTO.login())) {
            throw new IllegalArgumentException("Email already in use: " + userDTO.login());
        }

        var user = User.builder()
            .email(userDTO.email())
            .login(userDTO.login())
            .password(userDTO.password())
            .build();

        return userRepository.save(user);
    }

    public User createUser(User user) {
        if(userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(Long id, UserDTO userDTO) {
        return userRepository.findById(id)
            .map(user -> {
                user.setLogin(userDTO.login());
                user.setPassword(userDTO.password());
                return userRepository.save(user);
            })
            .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if(!userRepository.existsById(id)) {
            throw new NoSuchElementException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
