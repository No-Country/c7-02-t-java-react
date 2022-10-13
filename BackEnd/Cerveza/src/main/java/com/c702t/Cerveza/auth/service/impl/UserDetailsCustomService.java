package com.c702t.Cerveza.auth.service.impl;

import com.c702t.Cerveza.auth.utility.RoleEnum;
import com.c702t.Cerveza.models.entity.RoleEntity;
import com.c702t.Cerveza.models.entity.UserEntity;
import com.c702t.Cerveza.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsCustomService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @SuppressWarnings("unused")
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> userDB = userRepository.findByEmail(email);

        if (userDB.isEmpty()) {
            throw new UsernameNotFoundException(email);
        }

        UserEntity user = userDB.get();
        return new User(userDB.get().getEmail(), userDB.get().getPassword(), mapRoles(userDB.get().getRoleId()));

    }

    private Collection<? extends GrantedAuthority> mapRoles(Set<RoleEntity> roles) {
        return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getName())).collect(Collectors.toList());
    }


}
