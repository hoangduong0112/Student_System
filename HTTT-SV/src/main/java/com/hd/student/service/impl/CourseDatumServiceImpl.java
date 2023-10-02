package com.hd.student.service.impl;

import com.hd.student.repository.CourseDatumRepository;
import com.hd.student.service.CourseDatumService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseDatumServiceImpl implements CourseDatumService {

    @Autowired
    private CourseDatumRepository courseDatumRepository;
    @Autowired
    private ModelMapper modelMapper;

}
