package com.epam.spring.core.service.impl;

import com.epam.spring.core.dao.UserDAO;
import com.epam.spring.core.domain.User;
import com.epam.spring.core.repository.UserRepository;
import com.epam.spring.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Component("userService")
public class UserServiceImp implements UserService {

    @Autowired
    private UserDAO userDAO;

    private UserRepository userRepository;

    UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.add(user);
    }

    @Override
    public void remove(Long id) {
        userRepository.remove(id);
    }

    @Override
    public User getById(Long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public Collection<User> getAll() {
        return userRepository.getMap().entrySet().stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
