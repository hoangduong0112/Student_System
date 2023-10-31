package com.hd.student.service;

import com.hd.student.entity.User;
import com.hd.student.payload.response.UserInfoResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    void saveUser(User user);

    UserInfoResponse getCurrentUserInfo(String email);

    List<UserInfoResponse> findAll(int majorId);

}
