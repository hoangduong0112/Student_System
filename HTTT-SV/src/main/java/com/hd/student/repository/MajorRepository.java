package com.hd.student.repository;

import com.hd.student.entity.Major;
import com.hd.student.payload.response.MajorResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MajorRepository extends JpaRepository<Major, Integer> {
    List<Major> findByDepartment_Id(Integer id);

}