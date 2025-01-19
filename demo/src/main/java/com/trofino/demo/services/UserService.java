package com.trofino.demo.services;

import com.trofino.demo.domain.User;

import java.util.List;

public interface UserService {

    User findById(Integer id);
    List<User>findAll();
}
