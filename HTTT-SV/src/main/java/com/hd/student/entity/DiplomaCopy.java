package com.hd.student.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "diploma_copy")
public class DiplomaCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diploma_copy_id", nullable = false)
    private Integer id;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "online_service_id")
    private OnlineService onlineService;

}