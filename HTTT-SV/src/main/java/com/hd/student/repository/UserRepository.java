package com.hd.student.repository;

import com.hd.student.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByMajor_Id(Integer id);
    User findByEmail(String email);
}