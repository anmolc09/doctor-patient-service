package com.learning.rest;

import com.learning.service.InvoiceService;
import com.learning.shared.InvoiceModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;
    @PostMapping("get-invoice")
    public ResponseEntity<byte[]> getInvoice(@RequestBody InvoiceModel invoice)
    {
        return invoiceService.getInvoice(invoice);
    }

}
