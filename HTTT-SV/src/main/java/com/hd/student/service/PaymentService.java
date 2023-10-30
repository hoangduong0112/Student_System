package com.hd.student.service;

import com.hd.student.payload.response.ApiResponse;
import com.hd.student.payload.response.PaymentResponse;
import com.hd.student.payload.response.TransactionPaymentResponse;

import java.text.ParseException;

public interface PaymentService {
    PaymentResponse createPayment(int onlineServiceId, int userId, String url);
    TransactionPaymentResponse getStatusAfterPay(String amount, String title, String date, String success, String IdRef)
            throws ParseException;

    PaymentResponse getById(int id);

    PaymentResponse verifyPayment(int id);

    PaymentResponse getByOnlineServiceId(int serviceId);
}
