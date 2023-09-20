package com.hd.student.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "online_service")
public abstract class OnlineService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "online_service_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "created_date")
    private Instant createdDate;

    @Column(name = "status", length = 45)
    private String status;

    @Column(name = "is_shipped")
    private Boolean isShipped;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "service_cate_id", nullable = false)
    private ServiceCate serviceCate;

}