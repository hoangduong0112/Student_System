package com.hd.student.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "lecture")
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_id", nullable = false)
    private Integer id;

    @Column(name = "lecture_name", nullable = false, length = 45)
    private String lectureName;

    @Column(name = "lecture_phone", length = 45)
    private String lecturePhone;

    @OneToMany(mappedBy = "lecture")
    private Set<CourseDatum> courseData = new LinkedHashSet<>();

}