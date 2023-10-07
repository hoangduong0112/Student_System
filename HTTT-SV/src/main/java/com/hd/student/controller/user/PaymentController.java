package com.hd.student.controller.user;

import com.hd.student.exception.UnauthorizedException;
import com.hd.student.payload.response.PaymentResponse;
import com.hd.student.security.UserPrincipal;
import com.hd.student.service.PaymentService;
import com.hd.student.service.impl.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentController {


    @Autowired
    private PaymentService paymentService;

    @PostMapping ("/create-payment/{onlineServiceId}")
    public ResponseEntity<?> createPayment(@PathVariable int onlineServiceId, Authentication auth){
        UserPrincipal u = (UserPrincipal) auth.getPrincipal();
        PaymentResponse rp =  this.paymentService
                .createPayment(onlineServiceId, u.getId());

        return ResponseEntity.ok(rp);

    }

    @GetMapping("/payment-status")
    public ResponseEntity<?> getPaymentInfo(
            Authentication auth,
            @RequestParam(value = "vnp_Amount", required = false) String amount,
            @RequestParam(value = "vnp_OrderInfo", required = false) String title,
            @RequestParam(value = "vnp_PayDate", required = false) String date,
            @RequestParam(value = "vnp_TransactionStatus", required = false) String success) throws ParseException {
        return ResponseEntity.ok(this.paymentService.getStatusAfterPay(amount,title,date,success));
    }
}
