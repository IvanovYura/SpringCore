package com.epam.spring.core.service.impl;

import com.epam.spring.core.dao.UserDAO;
import com.epam.spring.core.domain.User;
import com.epam.spring.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component("userService")
public class UserServiceImp implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

    @Override
    public User save(User user) {
        return userDAO.add(user);
    }

    @Override
    public void remove(Long id) {
        userDAO.remove(id);
    }

    @Override
    public User getById(Long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public Collection<User> getAll() {
        return userDAO.getAll();
    }
}
