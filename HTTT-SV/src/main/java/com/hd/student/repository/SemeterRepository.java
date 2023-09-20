package com.hd.student.repository;

import com.hd.student.entity.Semeter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemeterRepository extends JpaRepository<Semeter, Integer> {
}