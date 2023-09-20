package com.hd.student.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "reg_subject")
public class RegSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reg_subject_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "semeter_subject_id", nullable = false)
    private SemeterSubject semeterSubject;

}