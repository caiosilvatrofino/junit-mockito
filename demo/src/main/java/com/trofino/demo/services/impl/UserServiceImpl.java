package com.trofino.demo.services.impl;

import com.trofino.demo.domain.User;
import com.trofino.demo.repositories.UserRepository;
import com.trofino.demo.services.UserService;
import com.trofino.demo.services.exceptions.ObjectNotFoundExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User findById(Integer id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundExceptions("Objeto não encontrado!"));
    }
}
