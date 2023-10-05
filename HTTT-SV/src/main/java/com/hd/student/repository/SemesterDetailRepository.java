package com.hd.student.repository;

import com.hd.student.entity.SemesterDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SemesterDetailRepository extends JpaRepository<SemesterDetail, Integer> {
    List<SemesterDetail> findBySemesterUser_Id(Integer id);
}