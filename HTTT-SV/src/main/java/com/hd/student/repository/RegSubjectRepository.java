package com.hd.student.repository;

import com.hd.student.entity.RegSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegSubjectRepository extends JpaRepository<RegSubject, Integer> {
}