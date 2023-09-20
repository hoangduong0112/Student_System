package com.hd.student.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "diploma_copy")
public class DiplomaCopy extends OnlineService{

    @Column(name = "copy")
    private Integer copy;

    @Column(name = "phone_contact", length = 45)
    private String phoneContact;

    @Column(name = "email", length = 45)
    private String email;

    @Column(name = "diploma_year")
    private Integer diplomaYear;

    @Column(name = "diploma_code", length = 45)
    private String diplomaCode;

}