package com.hd.student.service.impl;

import com.hd.student.entity.Department;
import com.hd.student.exception.ResourceNotFoundException;
import com.hd.student.payload.response.DepartmentResponse;
import com.hd.student.repository.DepartmentRepository;
import com.hd.student.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private ModelMapper modelMapper;



    @Override
    public List<DepartmentResponse> findAllDepartment() {
        modelMapper.typeMap(Department.class, DepartmentResponse.class)
                .addMappings(mapper -> mapper.skip(DepartmentResponse::setMajors));
        return this.departmentRepository.findAll().stream()
                .map((element) -> modelMapper.map(element, DepartmentResponse.class)).toList();
    }

    @Override
    public DepartmentResponse findDepartmentById(int id) {
        Department department = this.departmentRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Không tìm thấy Khoa", "id", id)
        );
        modelMapper.typeMap(Department.class, DepartmentResponse.class).addMapping(
                Department::getMajors, DepartmentResponse::setMajors
        );
        return modelMapper.map(department, DepartmentResponse.class);
    }


}
