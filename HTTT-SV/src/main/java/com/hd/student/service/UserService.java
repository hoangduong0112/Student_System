package com.hd.student.service;

import com.hd.student.entity.User;
import com.hd.student.payload.response.UserInfoResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    public void saveUser(User user);

    public UserInfoResponse getCurrentUserInfo(String email);

    public List<UserInfoResponse> findAll(int majorId);

}
