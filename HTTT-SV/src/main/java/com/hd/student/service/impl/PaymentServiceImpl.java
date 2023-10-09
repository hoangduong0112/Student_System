package com.hd.student.service.impl;

import com.hd.student.configs.VNPayConfig;
import com.hd.student.entity.*;
import com.hd.student.entity.enums.PaymentStatus;
import com.hd.student.entity.enums.ServiceStatus;
import com.hd.student.exception.AccessDeniedException;
import com.hd.student.exception.ResourceExistException;
import com.hd.student.exception.ResourceNotFoundException;
import com.hd.student.payload.response.ApiResponse;
import com.hd.student.payload.response.PaymentResponse;
import com.hd.student.payload.response.TransactionPaymentResponse;
import com.hd.student.repository.OnlineServiceRepository;
import com.hd.student.repository.PaymentRepository;
import com.hd.student.service.PaymentService;
import jakarta.servlet.ServletException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
        if (!onlineService.getStatus().equals(ServiceStatus.PENDING))
            throw new RuntimeException("Yêu cầu đã được xử lý hủy bỏ");
        Optional<Payment> payment = paymentRepository.findByServiceOnline_Id(onlineServiceId);
        if (payment.isPresent())
            throw new ResourceExistException("Đã có thanh toán, vui lòng kiểm tra tình trạng", "");

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
            ps.setVnpayTxnred(VNPayConfig.getRandomNumber(8));
            String resUrl = "";
            try{
                resUrl = com.hd.student.utils.VNPayUtil.CreatePayment(ps);
                ps.setPaymentStatus(PaymentStatus.PAID);
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

    @Override
    public TransactionPaymentResponse getStatusAfterPay(String amount, String title, String date, String success) throws ParseException {
        TransactionPaymentResponse rp = new TransactionPaymentResponse();
        if(success.equals("00"))
            rp.setStatus("Success");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        formatter = formatter.withZone(ZoneId.of("GMT+7"));
        LocalDateTime newDate = LocalDateTime.parse(date, formatter);
        rp.setDate(newDate);
        rp.setTitle(title);
        rp.setAmount(Double.parseDouble(amount));

        return rp;

    }

    @Override
    public PaymentResponse getFromOnlineServiceId(String username, int onlineServiceId) {
        Payment payment = paymentRepository.findByServiceOnline_Id(onlineServiceId).orElseThrow(()
                -> new ResourceNotFoundException("Không tìm thấy thanh toán của yêu cầu này", "id", onlineServiceId));
        String userOfPayment = payment.getServiceOnline().getUser().getEmail();
        if (username.equals(userOfPayment))
            return modelMapper.map(payment, PaymentResponse.class);
        throw new ResourceNotFoundException("Không tìm thấy thanh toán của yêu cầu này", "id", onlineServiceId);
    }

    @Override
    public ApiResponse verifyPayment(String username, String payment_id) {
        Payment payment = paymentRepository.findById(payment_id).orElseThrow(()
                -> new ResourceNotFoundException("Không tồn tại giao dịch", "id", payment_id));
        String userOfPayment = payment.getServiceOnline().getUser().getEmail();
        if (username.equals(userOfPayment)) {
            if (payment.getPaymentStatus() != PaymentStatus.PENDING)
                return new ApiResponse("Hóa đơn đang được thanh toán hoặc đã bị trì hoãn", true);

            try {
                Integer paid = com.hd.student.utils.VNPayUtil.querydrPayment(payment);

                if (paid == 0) {
                    payment.setPaymentStatus(PaymentStatus.PAID);
                    this.paymentRepository.save(payment);
                    return new ApiResponse("Bạn đã thanh toán xong dịch vụ.", true);
                }
                else if (paid == 2) {
                    payment.setPaymentStatus(PaymentStatus.CANCEL);
                    this.paymentRepository.save(payment);
                    return new ApiResponse("Yêu cầu đã bị hủy", true);
                }

                return new ApiResponse("Ticket is pending", true);
            } catch (RuntimeException | ServletException | IOException e) {
                e.printStackTrace();
            }
        }

        throw new ResourceNotFoundException("Không tìm thấy kết quả", "Mã Payment",payment_id);
    }

}
