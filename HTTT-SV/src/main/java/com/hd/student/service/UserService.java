package com.hd.student.service;

import com.hd.student.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.HashSet;

public interface UserService extends UserDetailsService {

    public void saveUser(User user);

}
