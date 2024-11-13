package com.journey_back.service;


import com.journey_back.model.UserModel;
import com.journey_back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    // Construtor
    public UserService(UserRepository repository) {
        this.userRepository = repository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }


    // Listar Usuarios
    public List<UserModel> getUsers() {
        List<UserModel> list = userRepository.findAll();
        return list;
    }

    // Cadastrar Usuario
    public UserModel postUser(UserModel user) {
        if (!userRepository.existsByEmail(user.getEmail())) {
            String encoder = passwordEncoder.encode(user.getPassword());
            user.setPassword(encoder);
            UserModel newUser = userRepository.save(user);
            return newUser;
        } else {
            throw new RuntimeException("email inserido já cadastrado");
        }
    }

    // Deletar Usuario
    public boolean deleteUser(UUID id) {
        var user = userRepository.findById(id);
        if(user.isPresent()) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


}
