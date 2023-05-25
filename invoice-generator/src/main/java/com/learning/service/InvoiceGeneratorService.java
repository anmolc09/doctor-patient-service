package com.learning.service;

import com.learning.entity.InvoiceEntity;
import com.lowagie.text.*;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
@Slf4j
public class InvoiceGeneratorService {

    public byte[] createAppointmentInvoicePdf(InvoiceEntity invoice) {

        log.info("Creating appointment invoice PDF...");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, outputStream);

        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        Paragraph paragraph = new Paragraph("Invoice", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph);

        PdfPTable table1 = new PdfPTable(2);
        table1.setWidthPercentage(100f);
        table1.setWidths(new int[]{10, 10});
        table1.setSpacingBefore(20);

        String patientInfo = "Patient: \n" + invoice.getPatient().getName() + "\n"
                           + invoice.getPatient().getAddress()
                           + "\n Phone: " + invoice.getPatient().getPhone();
        table1.addCell(new Paragraph(patientInfo));

        String invoiceInfo = "Date: \n" + invoice.getInvoiceDate() + "\n"
                           + "Invoice Id : " + invoice.getId()
                           + "\n Mode Of Payment :  " + invoice.getModeOfPayment();
        table1.addCell(new Paragraph(invoiceInfo));

        document.add(table1);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new int[]{3, 3, 3, 3});
        table.setSpacingBefore(5);

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(CMYKColor.YELLOW);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(CMYKColor.BLACK);

        cell.setPhrase(new Phrase("Total Amount", font));
        table.addCell(cell);
        table.addCell(invoice.getTotalAmount().toString());
        cell.setPhrase(new Phrase("status", font));
        table.addCell(cell);

        table.addCell(invoice.getStatus());

        document.add(table);
        document.close();

        return outputStream.toByteArray();
    }

}