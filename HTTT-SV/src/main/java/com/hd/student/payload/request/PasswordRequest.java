package com.hd.student.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordRequest {
    private String oldPassword;
    private String newPassword;

    private String newPasswordAgain;
}
