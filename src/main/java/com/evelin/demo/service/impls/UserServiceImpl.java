package com.evelin.demo.service.impls;

import com.evelin.demo.data.dtos.UserDTO;
import com.evelin.demo.data.entities.User;
import com.evelin.demo.repos.UserRepository;
import com.evelin.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
private final UserRepository userRepository;
private final ModelMapper modelMapper;

@Autowired
public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerUser(UserDTO userDTO) {
        User user = this.modelMapper
                .map(userDTO, User.class);

        user.setRole(this.userRepository.count()==0 ? "Admin" : "Contributor");

        System.out.println("Saving user");
        this.userRepository.saveAndFlush(user);
        System.out.println("User is saved");
    }
}
