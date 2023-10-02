package com.hd.student.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "service_cate")
public class ServiceCate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_cate_id", nullable = false)
    private Integer id;

    @Column(name = "service_cate_name", length = 45)
    private String serviceCateName;

    @Column(name = "price")
    private Double price;

    @Column(name = "is_available")
    private Boolean isAvailable;

    @Column(name = "description", length = 45)
    private String description;

    @OneToMany(mappedBy = "serviceCate")
    private Set<OnlineService> onlineServices = new LinkedHashSet<>();

}