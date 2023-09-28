package com.hd.student.repository;

import com.hd.student.entity.OnlineService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OnlineServiceRepository extends JpaRepository<OnlineService, Integer> {
    List<OnlineService> findAllByUserId(Integer userId);
}