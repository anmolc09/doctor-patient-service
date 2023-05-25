package com.learning.shared;

import com.learning.models.PatientModel;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
@Data
public class InvoiceModel {
    private Long id;
    private PatientModel patient;
    private Double totalAmount;
    private LocalDate invoiceDate;
    private String ModeOfPayment;
    private String status;
}
