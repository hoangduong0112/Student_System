package com.hd.student.service;

import com.hd.student.entity.Department;
import com.hd.student.entity.Major;
import com.hd.student.payload.response.MajorResponse;

import java.util.List;

public interface MajorService {
    List<MajorResponse> getAll();
    List<MajorResponse> getByDepartmentId(int id);
}
