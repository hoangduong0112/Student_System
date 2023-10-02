package com.hd.student.repository;

import com.hd.student.entity.CourseData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDatumRepository extends JpaRepository<CourseData, Integer> {
}