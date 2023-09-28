package com.hd.student.repository;

import com.hd.student.entity.ScheduleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleInfoRepository extends JpaRepository<ScheduleInfo, Integer> {
}