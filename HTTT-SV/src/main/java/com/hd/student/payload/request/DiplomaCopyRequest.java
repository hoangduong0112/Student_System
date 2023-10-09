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
public class DiplomaCopyRequest {
    @NotNull
    private Integer copy;
    @NotNull
    @NotBlank
    private String phoneContact;
    @NotNull
    private String email;
    @NotNull
    private Integer diplomaYear;
    @NotNull
    private String diplomaCode;
}
