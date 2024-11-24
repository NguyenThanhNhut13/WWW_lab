/*
 * @ (#) UserServiceImpl.java       1.0     08/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.services.impl;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 08/11/2024
 * @version:    1.0
 */

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.converters.UserMapper;
import vn.edu.iuh.fit.backend.dtos.UserDTO;
import vn.edu.iuh.fit.backend.models.Role;
import vn.edu.iuh.fit.backend.models.User;
import vn.edu.iuh.fit.backend.repositories.UserRepository;
import vn.edu.iuh.fit.backend.services.UserService;

import java.util.Collection;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), rolesToGrantedAuthorities(user.getRoles()));
        System.out.println("User: " + userDetails.getUsername() + " has roles: " + userDetails.getAuthorities());
        return userDetails;
    }

    private Collection<? extends GrantedAuthority> rolesToGrantedAuthorities(Collection<Role> roles) {
        roles.forEach(role -> {
            System.out.println("Original role from DB: " + role.getRoleName());
        });

        Collection<GrantedAuthority> authorities = roles.stream()
                .map(role -> {
                    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
                    System.out.println("Mapped to authority: " + authority.getAuthority());
                    return authority;
                })
                .collect(Collectors.toList());

        System.out.println("Final authorities: " + authorities);
        return authorities;
    }

    @Override
    public UserDTO getUserByUsername(String name) {
        return userMapper.toDTO(userRepository.findByUsername(name).orElse(null));
    }

}
