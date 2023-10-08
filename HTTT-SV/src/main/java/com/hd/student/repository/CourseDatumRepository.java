package com.hd.student.repository;

import com.hd.student.entity.CourseDatum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseDatumRepository extends JpaRepository<CourseDatum, Integer> {
    List<CourseDatum> findByCourse_CourseNameContainsIgnoreCase(String courseName);
}