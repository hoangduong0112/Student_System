package com.hd.student.entity;

import com.hd.student.entity.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_online_id")
    private OnlineService serviceOnline;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "payment_status", length = 50)
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(name = "price")
    private Double price;

    @Column(name = "vnpay_txnref")
    private String vnpayTxnred;

}