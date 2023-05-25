package com.learning.client;

import com.learning.models.PatientModel;
import com.learning.shared.InvoiceModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "invoice-generator", url = "${invoice-generator}")
public interface InvoiceClient {

    @PostMapping("/get-invoice")
    byte[] createAppointmentInvoice(@RequestBody InvoiceModel invoice);

    //getinvoiceByAppointmentId
}
