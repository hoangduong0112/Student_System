package com.hd.student.repository;

import com.hd.student.entity.StudyRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudyRoomRepository extends JpaRepository<StudyRoom, Integer> {
    List<StudyRoom> findByIsAvailable(Boolean isAvailable);
    boolean existsByStudyRoomNameIgnoreCase(String studyRoomName);
}