package com.hd.student.service;

import com.hd.student.payload.response.DepartmentResponse;

import java.util.List;

public interface DepartmentService {
    List<DepartmentResponse> findAllDepartment();

    DepartmentResponse findDepartmentById(int id);
}
