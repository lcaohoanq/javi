package com.lcaohoanq.demo.domain.user;


public interface IUserService {
    User create(UserDTO userDTO);
    User update(Long id, UserDTO userDTO);
    void delete(Long id);
    User findById(Long id);
}
