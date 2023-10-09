package com.hd.student.payload.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceCateRequest {
    @NotNull
    private String serviceCateName;
    @NotNull
    private Double price;
    @NotNull
    private Boolean isAvailable;
    @NotNull
    private String description;
}
