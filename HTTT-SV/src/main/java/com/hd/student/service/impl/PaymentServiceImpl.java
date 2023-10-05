package com.hd.student.service.impl;

import com.hd.student.configs.VNPayConfig;
import com.hd.student.entity.*;
import com.hd.student.exception.AccessDeniedException;
import com.hd.student.exception.ResourceNotFoundException;
import com.hd.student.payload.response.PaymentResponse;
import com.hd.student.payload.response.TransactionPaymentResponse;
import com.hd.student.repository.OnlineServiceRepository;
import com.hd.student.repository.PaymentRepository;
import com.hd.student.service.PaymentService;
import com.hd.student.utils.VNPayUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Service
public class PaymentServiceImpl implements PaymentService {


    @Autowired
    private OnlineServiceRepository onlineServiceRepository;
    @Autowired
    private com.hd.student.utils.VNPayUtil VNPayUtil;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public PaymentResponse createPayment(int onlineServiceId, int userId){
        OnlineService onlineService = this.onlineServiceRepository.findById(onlineServiceId)
                .orElseThrow(()-> new ResourceNotFoundException("Không tìm thấy yêu cầu", "mã yêu cầu",onlineServiceId));
        Payment ps = new Payment();
        ps.setServiceOnline(onlineService);
        ps.setPaymentStatus(PaymentStatus.PENDING);
        if(onlineService.getUser().getId() == userId){
            ps.setCreatedDate(new Date());
            switch (onlineService.getServiceCate().getId()) {
                case 1:
                    Transcript tr = onlineService.getTranscript();
                    ps.setPrice(onlineService.getServiceCate().getPrice()*tr.getQuantity());
                    break;
                case 2:
                    StudCertification sc = onlineService.getStudCertification();
                    ps.setPrice(onlineService.getServiceCate().getPrice()*sc.getEngCopy());
                    break;
                case 3:
                    DiplomaCopy dc = onlineService.getDiplomaCopy();
                    ps.setPrice(onlineService.getServiceCate().getPrice()*dc.getCopy());
                    break;
                case 5:
                    UnlockStudent us = onlineService.getUnlockStudent();
                    ps.setPrice(50000d);
                    break;
                default:
                    throw new RuntimeException("Lỗi không rõ");
            }
            ps.setId(VNPayConfig.getRandomNumber(8));
            String resUrl = "";
            try{
                resUrl = this.VNPayUtil.CreatePayment(ps);
                ps.setPaymentStatus(PaymentStatus.COMPLETED);
            } catch (UnsupportedEncodingException e) {
                ps.setPaymentStatus(PaymentStatus.CANCEL);
            }
            Payment save = paymentRepository.save(ps);
            PaymentResponse rp = modelMapper.map(save, PaymentResponse.class);
            rp.setUrl(resUrl);
            return rp;
        }
        else throw new AccessDeniedException("Không có quyền làm điều này");
    }

    public TransactionPaymentResponse getStatusAfterPay(String amount, String title, String date, String success) throws ParseException {
        TransactionPaymentResponse rp = new TransactionPaymentResponse();
        if(success.equals("00"))
            rp.setStatus("Success");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Date newDate = formatter.parse(date);
        rp.setDate(newDate);
        rp.setTitle(title);
        rp.setAmount(Double.parseDouble(amount));

        return rp;

    }
}
