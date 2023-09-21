package com.hd.student.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transcript")
public class Transcript extends OnlineService{

    @Column(name = "language", length = 45)
    private String language;

    @Column(name = "from_semeter", nullable = false)
    private Integer fromSemeter;

    @Column(name = "to_semeter", nullable = false)
    private Integer toSemeter;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "contact_phone", length = 45)
    private String contactPhone;

    @Column(name = "is_sealed")
    private Boolean isSealed;

}