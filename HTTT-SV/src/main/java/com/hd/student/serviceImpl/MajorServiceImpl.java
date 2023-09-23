package com.hd.student.serviceImpl;

import com.hd.student.entity.Department;
import com.hd.student.entity.Major;
import com.hd.student.repository.MajorRepository;
import com.hd.student.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MajorServiceImpl implements MajorService {

    @Autowired
    private MajorRepository majorRepository;

    @Override
    public Department findByMajorId(Integer majorId) {
        Major major = majorRepository.findMajorById(majorId);
        if (major != null) {
            return major.getDepartment();
        }
        return null;
    }
}
