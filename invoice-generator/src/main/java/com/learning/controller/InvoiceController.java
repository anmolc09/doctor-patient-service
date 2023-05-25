package com.learning.controller;

import com.learning.entity.InvoiceEntity;
import com.learning.service.InvoiceGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceGeneratorService invoiceGeneratorService;

    @PostMapping("/get-invoice")
    public byte[] createAppointmentInvoice(@RequestBody InvoiceEntity invoice){
        return invoiceGeneratorService.createAppointmentInvoicePdf(invoice);
    }

}
