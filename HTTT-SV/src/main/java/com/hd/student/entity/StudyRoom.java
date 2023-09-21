package com.hd.student.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
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

}