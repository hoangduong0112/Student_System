package com.hd.student.serviceImpl;

import com.hd.student.entity.Role;
import com.hd.student.entity.User;
import com.hd.student.payload.UserInfoResponse;
import com.hd.student.repository.UserRepository;
import com.hd.student.security.UserPrincipal;
import com.hd.student.service.MajorService;
import com.hd.student.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private MajorService majorService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(email);
        return UserPrincipal.create(user);
    }

    @Override
    public UserInfoResponse getCurrentUserInfo(String email) {
        User user = userRepository.findByEmail(email);
        UserInfoResponse info = new UserInfoResponse();

        info.setId(user.getId());
        info.setFullName(user.getFullName());
        info.setAvatar(user.getAvatar());
        info.setEmail(user.getEmail());
        info.setRole(user.getUserRole());
        info.setMajor_name(user.getMajor().getMajorName());

        int majorId = user.getMajor().getId();
        info.setDepartment_name(majorService.findByMajorId(majorId).getDepartmentName());

        return info;

    }

    public UserServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserRole(Role.USER); //default user role
        this.userRepository.save(user);
    }
}
