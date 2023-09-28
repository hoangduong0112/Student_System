package com.hd.student.repository;

import com.hd.student.entity.DiplomaCopy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiplomaCopyRepository extends JpaRepository<DiplomaCopy, Integer> {
    DiplomaCopy findByOnlineServiceId(int id);
}