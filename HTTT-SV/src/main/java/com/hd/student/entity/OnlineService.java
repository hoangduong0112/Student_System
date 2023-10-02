package com.hd.student.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "online_service")
public class OnlineService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "online_service_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "status", length = 45)
    @Enumerated(EnumType.STRING)
    private ServiceStatus status;

    @Column(name = "is_shipped")
    private Boolean isShipped;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "service_cate_id", nullable = false)
    private ServiceCate serviceCate;

    @OneToOne(mappedBy = "onlineService")
    private DiplomaCopy diplomaCopy;

    @OneToOne(mappedBy = "onlineService")
    private StudCertification studCertification;

    @OneToOne(mappedBy = "onlineService")
    private Transcript transcript;

    @OneToOne(mappedBy = "onlineService")
    private UnlockStudent unlockStudent;

}