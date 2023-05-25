package com.learning.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "invoice")
public class InvoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientEntity patient;
    private Double totalAmount;
    private LocalDate invoiceDate;
    private String ModeOfPayment;
    private String status;

}
