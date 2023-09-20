package com.hd.student.repository;

import com.hd.student.entity.SemeterSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemeterSubjectRepository extends JpaRepository<SemeterSubject, Integer> {
}