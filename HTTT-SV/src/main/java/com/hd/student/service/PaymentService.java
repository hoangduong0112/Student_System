package com.hd.student.service;

import com.hd.student.payload.response.ApiResponse;
import com.hd.student.payload.response.PaymentResponse;
import com.hd.student.payload.response.TransactionPaymentResponse;

import java.text.ParseException;

public interface PaymentService {
    PaymentResponse createPayment(int onlineServiceId, int userId);
    TransactionPaymentResponse getStatusAfterPay(String amount, String title, String date, String success)
            throws ParseException;

    PaymentResponse getFromOnlineServiceId(String username, int onlineServiceId);

    ApiResponse verifyPayment(String username, String payment_id);
}
