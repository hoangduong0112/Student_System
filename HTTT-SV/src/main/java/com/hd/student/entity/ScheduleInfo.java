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
@Table(name = "schedule_info")
public class ScheduleInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_info_id", nullable = false)
    private Integer id;

    @Column(name = "weekdays", length = 45)
    @Enumerated(EnumType.STRING)
    private Weekdays weekdays;

    @Column(name = "start_at")
    private Integer startAt;

    @Column(name = "end_at")
    private Integer endAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_room")
    private StudyRoom studyRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private CourseDatum course;

}