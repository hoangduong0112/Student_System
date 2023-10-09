package com.hd.student.repository;

import com.hd.student.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    Optional<Course> findByCourseNameIgnoreCase(String courseName);
    List<Course> findByCourseNameContainsIgnoreCase(String courseName);
}