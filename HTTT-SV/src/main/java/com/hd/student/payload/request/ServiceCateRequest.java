package com.hd.student.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceCateRequest {
    private String serviceCateName;

    private Double price;

    private Boolean isAvailable;

    private String description;
}
