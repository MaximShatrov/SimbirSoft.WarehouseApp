package com.simbirsoft.shatrov.warehouseApp.service.user;

import com.simbirsoft.shatrov.warehouseApp.service.Exceptions.*;
import com.simbirsoft.shatrov.warehouseApp.entity.User;
import com.simbirsoft.shatrov.warehouseApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(User user) throws NullEntityException, EntityAlreadyExistsException {
        if (user == null) {
            throw new NullEntityException("User is NULL");
        }
        if (userRepository.existsById(user.getLogin())) {
            throw new EntityAlreadyExistsException("User is already exists!");
        }
        userRepository.save(user);
    }

    @Override
    public User readUser(String login) throws EntityNotFoundException {
        if (userRepository.existsById(login)) {
            return userRepository.findById(login).get();
        }
        throw new EntityNotFoundException("User with login:" + login + " not found.");
    }

    @Override
    public void updateUser(User user) throws EntityNotFoundException, NullEntityException {
        if (user == null) {
            throw new NullEntityException("User id NULL");
        }
        if (userRepository.existsById(user.getLogin())) {
            userRepository.save(user);
            return;
        }
        throw new EntityNotFoundException("User with login:" + user.getLogin() + " not found.");
    }

    @Override
    public void deleteUser(String login) throws EntityNotFoundException {
        if (userRepository.existsById(login)) {
            userRepository.deleteById(login);
            return;
        }
        throw new EntityNotFoundException("User with login:" + login + " not found.");
    }

    @Override
    public List<User> findAll() throws EntityNotFoundException {
        if (userRepository.findAll().isEmpty()) {
            throw new EntityNotFoundException("DB is empty.");
        }
        return userRepository.findAll();
    }
}
