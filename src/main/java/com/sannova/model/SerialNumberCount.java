package com.sannova.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "serial_number_count")
public class SerialNumberCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "serial_count")
    private Integer serialCount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_details_id")
    private FormPrintDetails formPrintDetails;

}
