package com.c702t.Cerveza.auth.config.seeder;

import com.c702t.Cerveza.models.request.UserRequest;
import com.c702t.Cerveza.repository.UserRepository;
import com.c702t.Cerveza.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.stream.IntStream;

//@Component
public class InitializerSeeder implements CommandLineRunner {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.findAll().isEmpty()) {
            this.createUsers(1, "user");
            this.createUsers(1, "business");
        }

    }

    private void createUsers(int users, String userType) {

        final String PASSWORD = "1234";
        final String CONFIRMPASSWORD = "1234";

        if (userType.equalsIgnoreCase("business")){
            IntStream.range(0, users).forEach(userNumber ->
                    create(userType, PASSWORD, CONFIRMPASSWORD, userNumber));
        }
        else{
            IntStream.range(0, users).forEach(userNumber ->
                    create(userType, PASSWORD, CONFIRMPASSWORD, userNumber));
        }

    }

    private void create(String userType, String PASSWORD, String CONFIRMPASSWORD, Integer userNumber) {
        try {
            authService.register(new UserRequest(userType + userNumber, userType + userNumber,
                    "email"+ userType + userNumber +"@mail.com", PASSWORD, CONFIRMPASSWORD,  userType +"Photo"+ userNumber +".png"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}