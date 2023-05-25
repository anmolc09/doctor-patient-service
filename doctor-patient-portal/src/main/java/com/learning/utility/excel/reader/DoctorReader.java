package com.learning.utility.excel.reader;

import com.learning.entity.DoctorEntity;
import com.learning.enums.ErrorMessages;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class DoctorReader {

    public List<DoctorEntity> getDoctorsList(InputStream file) {
        List<DoctorEntity> doctorEntityList = new ArrayList<>();
        try {
            //FileInputStream file = new FileInputStream(new File(".\\resources\\student-data.xlsx"));

            XSSFWorkbook workbook = new XSSFWorkbook(file);

            XSSFSheet sheet = workbook.getSheetAt(0);

            getDoctorList(sheet, doctorEntityList);

            file.close();
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return doctorEntityList;
    }

    private static void getDoctorList(XSSFSheet sheet, List<DoctorEntity> doctorEntityList) {
        for (int index = sheet.getFirstRowNum() + 1; index <= sheet.getLastRowNum(); index++) {
            Row row = sheet.getRow(index);

            DoctorEntity doctorEntity = DoctorEntity.builder().build();

            for (int index2 = row.getFirstCellNum(); index2 < row.getLastCellNum(); index2++) {
                Cell cell = row.getCell(index2);
                if (index2 == 0) {
                    doctorEntity.setId((long) cell.getNumericCellValue());
                } else if (index2 == 1) {
                    doctorEntity.setName(cell.getStringCellValue());
                } else if (index2 == 2) {
                    doctorEntity.setSpecialization(cell.getStringCellValue());
                } else if (index2 == 3) {
                    doctorEntity.setEmail(cell.getStringCellValue());
                } else if (index2 == 4) {
                    doctorEntity.setPhone(cell.getStringCellValue());
                } else if (index2 == 5) {
                    doctorEntity.setAddress(cell.getStringCellValue());
                } else {
                    log.error(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
                }
            }
            doctorEntityList.add(doctorEntity);
        }
    }
}


