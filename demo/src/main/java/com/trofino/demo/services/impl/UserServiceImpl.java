package com.trofino.demo.services.impl;

import com.trofino.demo.domain.User;
import com.trofino.demo.domain.dto.UserDTO;
import com.trofino.demo.repositories.UserRepository;
import com.trofino.demo.services.UserService;
import com.trofino.demo.services.exceptions.DataIntegratyViolationException;
import com.trofino.demo.services.exceptions.ObjectNotFoundExceptions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public User findById(Integer id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundExceptions("Objeto não encontrado!"));
    }

    @Override
    public List<User> findAll() {
       return repository.findAll();
    }

    @Override
    public User create(UserDTO user) {
        findByEmail(user);
        return repository.save(mapper.map(user, User.class));
    }

    @Override
    public User update(UserDTO obj) {
        findByEmail(obj);
        return repository.save(mapper.map(obj, User.class));
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);
    }

    private void findByEmail(UserDTO obj) {
        Optional<User> user = repository.findByEmail(obj.getEmail());
        if (user.isPresent() && !user.get().getId().equals(obj.getEmail())) {
            throw new DataIntegratyViolationException("Email já cadastrado no banco de dados");
        }
    }
}
