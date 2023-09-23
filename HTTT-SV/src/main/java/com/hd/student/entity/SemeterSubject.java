package com.hd.student.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "semeter_subject")
public class SemeterSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "semeter_subject_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Column(name = "start_date")
    private Instant startDate;

    @Column(name = "end_date")
    private Instant endDate;

    @Column(name = "student_quantity")
    private Integer studentQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "semeter_id")
    private Semeter semeter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_room_id")
    private StudyRoom studyRoom;

    @OneToMany(mappedBy = "semeterSubject")
    private Set<RegSubject> regSubjects = new LinkedHashSet<>();

}