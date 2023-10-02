package com.hd.student.service.impl;

import com.hd.student.entity.CourseDatum;
import com.hd.student.payload.request.CourseDatumRequest;
import com.hd.student.repository.CourseDatumRepository;
import com.hd.student.service.CourseDatumService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseDatumServiceImpl implements CourseDatumService {

    @Autowired
    private CourseDatumRepository courseDatumRepository;
    @Autowired
    private ModelMapper modelMapper;

}
