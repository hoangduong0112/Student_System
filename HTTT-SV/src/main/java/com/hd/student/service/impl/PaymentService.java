package com.hd.student.service.impl;

import com.hd.student.configs.VNPayConfig;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    public String createPayment(int total, String orderInfor, String urlReturn){
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String vnp_TxnRef = VNPayConfig.getRandomNumber(8);
//        long amount = Integer.parseInt(req.getParameter("amount"))*100;
        return null;
    }
}
