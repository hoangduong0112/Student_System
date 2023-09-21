package com.hd.student.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "semeter")
public class Semeter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "semeter_id", nullable = false)
    private Integer id;

    @Column(name = "semeter_name", nullable = false, length = 45)
    private String semeterName;

    @Column(name = "note", nullable = false, length = 45)
    private String note;

    @OneToMany(mappedBy = "semeter")
    private Set<SemeterSubject> semeterSubjects = new LinkedHashSet<>();

}