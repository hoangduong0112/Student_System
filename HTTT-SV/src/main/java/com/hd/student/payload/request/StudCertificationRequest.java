package com.hd.student.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudCertificationRequest {
    @NotNull
    private Integer vietCopy;
    @NotNull
    private Integer engCopy;
    @NotNull
    @NotBlank
    private String phoneContact;
    @NotNull
    private String email;
    @NotNull
    private String content;
}
