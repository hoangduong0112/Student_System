package com.hd.student.payload;

import com.hd.student.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponse {
    private int id;
    private String email;
    private String fullName;
    private String avatar;
    private Role role;
    private String major_name;
    private String department_name;

}
