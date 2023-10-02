package com.hd.student.repository;

import com.hd.student.entity.ServiceCate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceCateRepository extends JpaRepository<ServiceCate, Integer> {
    boolean existsById(int id);
}