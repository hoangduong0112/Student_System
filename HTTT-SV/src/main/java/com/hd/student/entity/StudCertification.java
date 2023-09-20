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
@Table(name = "stud_certification")
public class StudCertification extends OnlineService{

    @Column(name = "viet_copy")
    private Integer vietCopy;

    @Column(name = "eng_copy")
    private Integer engCopy;

    @Column(name = "phone_contact", length = 45)
    private String phoneContact;

    @Column(name = "email", length = 45)
    private String email;

    @Column(name = "content", length = 300)
    private String content;

}