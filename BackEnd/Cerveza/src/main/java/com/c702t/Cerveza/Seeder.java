package com.c702t.Cerveza;

import com.c702t.Cerveza.auth.utility.RoleEnum;
import com.c702t.Cerveza.models.entity.RoleEntity;
import com.c702t.Cerveza.models.entity.UserEntity;
import com.c702t.Cerveza.repository.RoleRepository;
import com.c702t.Cerveza.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class Seeder implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        RoleEntity roleEntity = null;
        UserEntity userEntity = null;
        Set<RoleEntity> roleEntitySet = new HashSet<>();

        if (roleRepository.findAll().isEmpty()) {
            roleEntity = RoleEntity.builder()
                    .name(RoleEnum.USER.getSimpleRoleName())
                    .description("Role assigned to beer consumers")
                    .build();
            roleEntitySet.add(roleRepository.save(roleEntity));

            // TODO Create RoleEntity with RoleEnum.BUSINESS
        }


        if (userRepository.findAll().isEmpty()) {

            userEntity = UserEntity.builder()
                    .email("user@mail.com")
                    .password(passwordEncoder.encode("root"))
                    .firstName("user")
                    .lastName("user")
                    .deleted(Boolean.FALSE)
                    .roleId(roleEntitySet)
                    .build();
            userRepository.save(userEntity);
        }
    }
}
