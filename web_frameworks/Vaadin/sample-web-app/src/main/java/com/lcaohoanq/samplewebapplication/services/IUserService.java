package com.lcaohoanq.samplewebapplication.services;

import com.lcaohoanq.samplewebapplication.dtos.UpdateUserDTO;
import com.lcaohoanq.samplewebapplication.dtos.UserDTO;
import com.lcaohoanq.samplewebapplication.models.User;
import java.util.List;

public interface IUserService {

    List<User> findAll();
    User login(String username, String password);
    User register(UserDTO userDTO);
    User save(User user);
    void softDelete(Long id);
    User findById(Long id);
    User update(Long id, UpdateUserDTO updatedUserDTO) throws Exception;
}
