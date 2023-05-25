/*
package com.learning.utility.excel.reader;

import com.learning.entity.AppointmentEntity;
import com.learning.entity.DoctorEntity;
import com.learning.models.DoctorModel;
import com.learning.repository.DoctorRepository;
import com.learning.service.DoctorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AppointmentReader {

    private final DoctorService doctorService;

    public List<AppointmentEntity> getAppointmentObjects(InputStream file) {
        List<AppointmentEntity> appointmentEntityList = new ArrayList<>();
        try {
            //FileInputStream file = new FileInputStream(new File(".\\resources\\student-data.xlsx"));

            XSSFWorkbook workbook = new XSSFWorkbook(file);

            XSSFSheet sheet = workbook.getSheetAt(0);

            getAppointmentList(sheet, appointmentEntityList);

            file.close();
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return appointmentEntityList;
    }

    private static void getAppointmentList(XSSFSheet sheet, List<AppointmentEntity> appointmentEntityList,DoctorService doctorService) {
        for (int index = sheet.getFirstRowNum() + 1; index <= sheet.getLastRowNum(); index++) {
            Row row = sheet.getRow(index);
            AppointmentEntity appointmentEntity = new AppointmentEntity();

            for (int index2 = row.getFirstCellNum(); index2 < row.getLastCellNum(); index2++) {
                Cell cell = row.getCell(index2);
                if (index2 == 0) {
                    appointmentEntity.setId((long) cell.getNumericCellValue());
                } else if (index2 == 1) {
                    Long doctorId =(long) cell.getNumericCellValue();
                    DoctorModel doctor = doctorService.getDoctorById(doctorId);
                    appointmentEntity.setDoctor();
                } else if (index2 == 2) {
                    appointmentEntity.setSpecialization(cell.getStringCellValue());
                } else if (index2 == 3) {
                    appointmentEntity.setEmail(cell.getStringCellValue());
                } else if (index2 == 4) {
                    appointmentEntity.setPhone(cell.getStringCellValue());
                } else if (index2 == 5) {
                    appointmentEntity.setAddress(cell.getStringCellValue());
                } else {
                    log.error("data not found ");
                }
            }
            doctorEntityList.add(doctorEntity);
        }
    }

}


*/
