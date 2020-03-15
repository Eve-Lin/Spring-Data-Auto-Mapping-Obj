package com.evelin.demo.controller;

import com.evelin.demo.data.dtos.UserDTO;
import com.evelin.demo.service.UserService;
import com.evelin.demo.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import java.io.BufferedReader;

@Component
public class AppController implements CommandLineRunner {

    private final BufferedReader bufferedReader;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final UserService userService;

    public AppController(BufferedReader bufferedReader, ModelMapper modelMapper, ValidationUtil validationUtil, UserService userService) {
        this.bufferedReader = bufferedReader;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {


        while (true) {
            String[] input = this.bufferedReader.readLine().split("\\|");

            switch (input[0]) {
                case "RegisterUser":

                    if (!input[2].equals(input[3])) {
                        System.out.println("Passwords don't match");
                        break;
                    }
                    UserDTO userDTO = new UserDTO(input[1], input[2], input[4]);

                    if (this.validationUtil.isValid(userDTO)) {
                        this.userService.registerUser(userDTO);
                    } else {
                        this.validationUtil
                                .getViolations(userDTO)
                                .stream()
                                .map(ConstraintViolation::getMessage)
                                .forEach(System.out::println);
                    }
                    System.out.println();
                    break;
            }

        }
    }
}
