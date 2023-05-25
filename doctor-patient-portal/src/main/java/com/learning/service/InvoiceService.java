package com.learning.service;

import com.learning.client.InvoiceClient;
import com.learning.constants.InvoiceConstants;
import com.learning.shared.InvoiceModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
@Slf4j
public class InvoiceService {

    private final InvoiceClient invoiceClient;

    public ResponseEntity<byte[]> getInvoice(@RequestBody InvoiceModel invoice){

        byte[] outputStream =  invoiceClient.createAppointmentInvoice(invoice);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder(InvoiceConstants.BUILDER_TYPE.getInvoiceMessage()).
                filename(String.format(InvoiceConstants.FILE_NAME.getInvoiceMessage(), invoice.getId())).build());
        return new ResponseEntity<>(outputStream, headers, HttpStatus.OK);
    }

}
