package com.hd.student.repository;

import com.hd.student.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findByCourseNameContainsIgnoreCase(String courseName);
    boolean existsByCourseNameLikeIgnoreCase(@NonNull String courseName);
}