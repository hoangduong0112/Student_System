package com.hd.student.service;

import com.hd.student.entity.Department;
import com.hd.student.entity.Major;

public interface MajorService {
    Department findByMajorId(Integer majorId);
}
