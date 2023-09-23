package com.hd.student.serviceImpl;

import com.hd.student.entity.ServiceCate;
import com.hd.student.repository.ServiceCateRepository;
import com.hd.student.service.ServiceCateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceCateServiceImpl implements ServiceCateService {

    @Autowired
    private ServiceCateRepository serviceCateRepository;

    @Override
    public List<ServiceCate> getAllDV() {
        return this.serviceCateRepository.findAll();
    }

}
