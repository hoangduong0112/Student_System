package com.hd.student.repository;

import com.hd.student.entity.StudyRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyRoomRepository extends JpaRepository<StudyRoom, Integer> {
}