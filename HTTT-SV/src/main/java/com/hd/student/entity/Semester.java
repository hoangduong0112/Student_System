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
@Table(name = "semester")
public class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "semester_id", nullable = false)
    private Integer id;

    @Column(name = "semester_name", nullable = false, length = 45)
    private String semesterName;

    @Column(name = "note", length = 45)
    private String note;

    @OneToMany(mappedBy = "semester")
    private Set<SemesterUser> semesterUsers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "fromSemester")
    private Set<Transcript> transcriptsFrom = new LinkedHashSet<>();

    @OneToMany(mappedBy = "toSemester")
    private Set<Transcript> transcriptsTo = new LinkedHashSet<>();

}