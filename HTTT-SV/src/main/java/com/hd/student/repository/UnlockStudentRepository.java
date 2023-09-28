package com.hd.student.repository;

import com.hd.student.entity.UnlockStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnlockStudentRepository extends JpaRepository<UnlockStudent, Integer> {
}