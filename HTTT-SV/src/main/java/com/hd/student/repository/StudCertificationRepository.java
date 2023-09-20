package com.hd.student.repository;

import com.hd.student.entity.StudCertification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudCertificationRepository extends JpaRepository<StudCertification, Integer> {
}