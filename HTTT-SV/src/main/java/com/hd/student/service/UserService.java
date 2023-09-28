package com.hd.student.service;

import com.hd.student.entity.User;
import com.hd.student.payload.response.UserInfoResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public void saveUser(User user);

    public UserInfoResponse getCurrentUserInfo(String email);

}
