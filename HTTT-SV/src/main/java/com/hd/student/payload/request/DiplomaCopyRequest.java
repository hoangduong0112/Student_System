package com.hd.student.payload.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiplomaCopyRequest {
    private Integer id;
    private Integer copy;
    private String phoneContact;

    private String email;

    private Integer diplomaYear;

    private String diplomaCode;
}
