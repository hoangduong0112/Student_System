package com.hd.student.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "course_data")
public class CourseDatum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_data_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "is_ended")
    private Boolean isEnded;

    @Column(name = "quantity")
    private Integer quantity;

    @OneToMany(mappedBy = "courseData")
    private Set<ScheduleInfo> scheduleInfos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "courseData")
    private Set<SemesterDetail> semesterDetails = new LinkedHashSet<>();

}