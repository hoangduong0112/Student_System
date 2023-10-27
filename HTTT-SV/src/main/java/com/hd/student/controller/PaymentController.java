package com.hd.student.controller;

import com.hd.student.payload.response.PaymentResponse;
import com.hd.student.security.UserPrincipal;
import com.hd.student.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.ParseException;

@RestController
@RequestMapping("/api/payment")
@Tag(name = "08. Payment", description = "Quản lý thanh toán")
@CrossOrigin
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Operation(
            summary = "Create Payment By OnlineServiceID",
            description = "Tạo thanh toán cho yêu cầu"
    )
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/create")
    public ResponseEntity<?> createPayment(@RequestParam(name = "service", required = true) int onlineServiceId, Authentication auth, HttpServletRequest rq){
        UserPrincipal u = (UserPrincipal) auth.getPrincipal();
        PaymentResponse rp =  this.paymentService
                .createPayment(onlineServiceId, u.getId(), "13.160.92.202");
        // servletRequest.getRemoteAddr()

        return ResponseEntity.ok(rp);

    }

    @Operation(
            summary = "Payment Result Return By VNPay",
            description = "Kết quả trả về của VNPay sau khi thanh toán"
    )
    @GetMapping("/result")
    public ResponseEntity<?> getPaymentInfo(
            @RequestParam(value = "vnp_Amount", required = false) String amount,
            @RequestParam(value = "vnp_OrderInfo", required = false) String title,
            @RequestParam(value = "vnp_TxnRef", required = false) String IdRef,
            @RequestParam(value = "vnp_PayDate", required = false) String date,
            @RequestParam(value = "vnp_TransactionStatus", required = false) String success) throws ParseException {
        return ResponseEntity.ok(this.paymentService.getStatusAfterPay(amount, title, date, success, IdRef));
    }
    //Get thogn tin thanh toan
    //authenticated
    @Operation(
            summary = "Get Payment By Id",
            description = "Lấy Payment theo Id"
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> getPaymentInfor(Authentication auth, @PathVariable int id){
        return ResponseEntity.ok(this.paymentService.getById(id));
    }

    @Operation(
            summary = "Verify Payment By Id",
            description = "Xác minh Payment theo Id"
    )
    @GetMapping("/{id}/verify")
    public ResponseEntity<?> verifyPaymentById(@PathVariable int id) {
        return ResponseEntity.ok().body(this.paymentService.verifyPayment(id));
    }
}
