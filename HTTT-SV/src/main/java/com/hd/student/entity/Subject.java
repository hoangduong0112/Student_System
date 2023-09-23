package com.hd.student.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id", nullable = false)
    private Integer id;

    @Column(name = "subject_name", length = 45)
    private String subjectName;

    @Column(name = "credits_num")
    private Integer creditsNum;

    @Column(name = "note", length = 45)
    private String note;

    @Column(name = "is_open")
    private Boolean isOpen;

    @OneToMany(mappedBy = "subject")
    private Set<SemeterSubject> semeterSubjects = new LinkedHashSet<>();

}