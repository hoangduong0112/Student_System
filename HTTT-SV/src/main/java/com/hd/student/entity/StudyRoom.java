package com.hd.student.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "study_room")
public class StudyRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_room_id", nullable = false)
    private Integer id;

    @Column(name = "study_room_name", length = 45)
    private String studyRoomName;

    @Column(name = "is_avaiable", length = 45)
    private String isAvaiable;

    @OneToMany(mappedBy = "studyRoom")
    private Set<SemeterSubject> semeterSubjects = new LinkedHashSet<>();

}