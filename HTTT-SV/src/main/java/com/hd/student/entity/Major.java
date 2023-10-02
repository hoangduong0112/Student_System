package com.hd.student.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "major")
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "major_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Column(name = "major_code", nullable = false, length = 45)
    private String majorCode;

    @Column(name = "major_name", nullable = false, length = 45)
    private String majorName;

    @OneToMany(mappedBy = "major")
    private Set<User> users = new LinkedHashSet<>();

}