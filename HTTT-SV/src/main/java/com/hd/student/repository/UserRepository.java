package com.hd.student.repository;

import com.hd.student.entity.User;
import com.hd.student.entity.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByUserRole(Role userRole);
    List<User> findByMajor_Id(Integer id);
    Optional<User> findByEmail(String email);
}