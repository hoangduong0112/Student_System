package com.hd.student.service;

import com.hd.student.entity.User;
import com.hd.student.payload.UserInfoResponse;
import com.hd.student.security.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.HashSet;

public interface UserService extends UserDetailsService {

    public void saveUser(User user);

    public UserInfoResponse getCurrentUserInfo(String email);

}
