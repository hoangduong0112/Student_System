package com.hd.student.service.impl;

import com.hd.student.entity.Department;
import com.hd.student.entity.Major;
import com.hd.student.exception.ResourceNotFoundException;
import com.hd.student.payload.response.MajorResponse;
import com.hd.student.repository.DepartmentRepository;
import com.hd.student.repository.MajorRepository;
import com.hd.student.service.MajorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MajorServiceImpl implements MajorService {

    @Autowired
    private MajorRepository majorRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<MajorResponse> getAll(){
        List<Major> majorList = this.majorRepository.findAll();
        return majorList.stream().map((element) -> modelMapper.map(element, MajorResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<MajorResponse> getByDepartmentId(int id){
        List<Major> majorList = this.majorRepository.findByDepartment_Id(id);
        return majorList.stream().map((element) -> modelMapper.map(element, MajorResponse.class))
                .collect(Collectors.toList());
    }
}
