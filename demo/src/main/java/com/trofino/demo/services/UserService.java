package com.trofino.demo.services;

import com.trofino.demo.domain.User;
import com.trofino.demo.domain.dto.UserDTO;

import java.util.List;

public interface UserService {

    User findById(Integer id);
    List<User>findAll();
    User create(UserDTO user);
}
