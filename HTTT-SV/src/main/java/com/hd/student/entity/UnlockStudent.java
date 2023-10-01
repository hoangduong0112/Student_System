package com.hd.student.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "unlock_student")
public class UnlockStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unlock_student_id", nullable = false)
    private Integer id;

    @Column(name = "content", length = 45)
    private String content;

    @Column(name = "image", length = 300)
    private String image;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "online_service_id")
    private OnlineService onlineService;

}