package com.hd.student.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "stud_certification")
public class StudCertification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stud_certification_id", nullable = false)
    private Integer id;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "online_service")
    private OnlineService onlineService;

}