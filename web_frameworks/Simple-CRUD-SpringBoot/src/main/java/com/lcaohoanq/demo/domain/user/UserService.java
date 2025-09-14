package com.lcaohoanq.demo.domain.user;


import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<User> findAll(Pageable pageable);
    List<User> findAll();
    User create(UserDTO userDTO);
    User createUser(User user);
    User update(Long id, UserDTO userDTO);
    void delete(Long id);
    User findById(Long id);
}
