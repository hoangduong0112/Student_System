package com.hd.student.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TranscriptRequest {
    @NotNull
    private String language;

    @NotNull
    private Integer fromSemester;
    @NotNull
    private Integer toSemester;

    @NotNull
    private Integer quantity;
    @NotNull
    @NotBlank
    private String contactPhone;
    @NotNull
    private Boolean isSealed;
}
