package com.hd.student.payload.response;

import com.hd.student.entity.ServiceCate;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OnlineServiceResponse {
    private Integer id;
    private LocalDate createdDate;
    private String status;
    private Boolean isShipped;

    private Integer serviceCateId;

}
