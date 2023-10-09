package com.hd.student.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceCateResponse {
    private Integer id;
    private String serviceCateName;

    private Double price;

    private Boolean isAvailable;

    private String description;
    private Integer numOfDate;


}
