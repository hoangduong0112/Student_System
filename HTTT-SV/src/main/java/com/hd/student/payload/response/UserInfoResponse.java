package com.hd.student.payload.response;

import com.hd.student.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
