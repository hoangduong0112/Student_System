package com.hd.student.service;

import com.hd.student.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public boolean addUser(User user);
}
