package com.hd.student.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id", nullable = false)
    private Integer id;

    @Column(name = "department_name", nullable = false, length = 45)
    private String departmentName;

    @Column(name = "description", length = 100)
    private String description;

    @OneToMany(mappedBy = "department")
    private Set<Major> majors = new LinkedHashSet<>();

}