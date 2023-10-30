package com.hd.student.service.impl;

import com.hd.student.entity.enums.Role;
import com.hd.student.entity.User;
import com.hd.student.payload.response.UserInfoResponse;
import com.hd.student.repository.UserRepository;
import com.hd.student.security.UserPrincipal;
import com.hd.student.service.MajorService;
import com.hd.student.service.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private MajorService majorService;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(email).orElseThrow(
                ()->new UsernameNotFoundException("User not found")
        );
        return UserPrincipal.create(user);
    }

    @Override
    public UserInfoResponse getCurrentUserInfo(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                ()->new UsernameNotFoundException("User not found"));

        modelMapper.typeMap(User.class, UserInfoResponse.class).addMapping(
                User-> User.getMajor().getMajorName(), UserInfoResponse::setMajor_name
        );

        modelMapper.typeMap(User.class, UserInfoResponse.class).addMapping(
                User-> User.getMajor().getDepartment().getDepartmentName(), UserInfoResponse::setDepartment_name
        );
        Converter<Role, String> roleStringConverter = ctx -> ctx.getSource() == null ? null : ctx.getSource().name();
        modelMapper.addConverter(roleStringConverter);



        return modelMapper.map(user, UserInfoResponse.class);

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

    @Override
    public List<UserInfoResponse> findAll(int majorId) {
        List<User> users;
        if(majorId != 0)
            users = this.userRepository.findByMajor_Id(majorId);
        else
            users = userRepository.findByUserRole(Role.USER);

        modelMapper.typeMap(User.class, UserInfoResponse.class).addMapping(
                User-> User.getMajor().getMajorName(), UserInfoResponse::setMajor_name
        );

        modelMapper.typeMap(User.class, UserInfoResponse.class).addMapping(
                User-> User.getMajor().getDepartment().getDepartmentName(), UserInfoResponse::setDepartment_name
        );
        Converter<Role, String> roleStringConverter = ctx -> ctx.getSource() == null ? null : ctx.getSource().name();
        modelMapper.addConverter(roleStringConverter);
        return users.stream().map((element) -> modelMapper.map(element, UserInfoResponse.class))
                .collect(Collectors.toList());
    }

}
