package com.hd.student.repository;

import com.hd.student.entity.SemesterUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SemesterUserRepository extends JpaRepository<SemesterUser, Integer> {
    List<SemesterUser> findAllByUserId(int userId);
}