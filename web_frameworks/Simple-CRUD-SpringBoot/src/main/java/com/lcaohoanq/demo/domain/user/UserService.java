package com.lcaohoanq.demo.domain.user;


public interface UserService {
    User create(UserDTO userDTO);
    User update(Long id, UserDTO userDTO);
    void delete(Long id);
    User findById(Long id);
}
