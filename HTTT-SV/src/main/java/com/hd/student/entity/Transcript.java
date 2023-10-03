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
@Table(name = "transcript")
public class Transcript {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transcript_id", nullable = false)
    private Integer id;

    @Column(name = "language", length = 45)
    private String language;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_semester")
    private Semester fromSemester;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_semester")
    private Semester toSemester;

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