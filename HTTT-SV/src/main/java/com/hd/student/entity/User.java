package com.hd.student.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @Column(name = "full_name", nullable = false, length = 45)
    private String fullName;

    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @Column(name = "password", length = 200)
    private String password;

    @Column(name = "user_role", length = 45)
    @Enumerated(EnumType.STRING)
    private Role userRole;

    @Column(name = "avatar", length = 200)
    private String avatar;

    @Column(name = "day_of_birth", length = 45)
    private String dayOfBirth;

    @Column(name = "gender", length = 45)
    private String gender;

    @Column(name = "phone", length = 45)
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_id")
    private Major major;

    @OneToMany(mappedBy = "user")
    private Set<OnlineService> onlineServices = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<RegSubject> regSubjects = new LinkedHashSet<>();

}