package com.hd.student.payload.response;

import com.hd.student.entity.ServiceCate;
import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OnlineServiceResponse {
    private Integer id;
    private LocalDate createdDate;
    private String status;
    private Boolean isShipped;
    private double price;
    private String serviceCateName;
//    private PaymentResponse payment;
}
