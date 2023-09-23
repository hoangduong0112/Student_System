package com.hd.student.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "transcript")
public class Transcript {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transcript_id", nullable = false)
    private Integer id;

    @Column(name = "language", length = 45)
    private String language;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_semeter")
    private Semeter fromSemeter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_semeter")
    private Semeter toSemeter;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "contact_phone", length = 45)
    private String contactPhone;

    @Column(name = "is_sealed")
    private Boolean isSealed;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "online_service_id")
    private OnlineService onlineService;

}