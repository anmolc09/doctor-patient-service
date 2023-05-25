package com.learning.utility.excel.reader;

import com.learning.entity.PatientEntity;
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
public class PatientReader {

    public List<PatientEntity> getPatientList(InputStream file) {
        List<PatientEntity> patientEntityList = new ArrayList<>();
        try {
            //FileInputStream file = new FileInputStream(new File(".\\resources\\student-data.xlsx"));

            XSSFWorkbook workbook = new XSSFWorkbook(file);

            XSSFSheet sheet = workbook.getSheetAt(0);

            getUserList(sheet, patientEntityList);

            file.close();
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return patientEntityList;
    }

    private static void getUserList(XSSFSheet sheet, List<PatientEntity> patientEntityList) {
        for (int index = sheet.getFirstRowNum() + 1; index <= sheet.getLastRowNum(); index++) {
            Row row = sheet.getRow(index);

            PatientEntity userEntity = PatientEntity.builder().build();

            for (int index2 = row.getFirstCellNum(); index2 < row.getLastCellNum(); index2++) {
                Cell cell = row.getCell(index2);
                if (index2 == 0) {
                    userEntity.setId((long) cell.getNumericCellValue());
                } else if (index2 == 1) {
                    userEntity.setName(cell.getStringCellValue());
                } else if (index2 == 2) {
                    userEntity.setGender(cell.getStringCellValue());
                } else if (index2 == 3) {
                    userEntity.setDateOfBirth(cell.getLocalDateTimeCellValue().toLocalDate());
                } else if (index2 == 4) {
                    userEntity.setEmail(cell.getStringCellValue());
                } else if (index2 == 5) {
                    userEntity.setPhone(cell.getStringCellValue());
                } else if (index2 == 6) {
                    userEntity.setAddress(cell.getStringCellValue());
                } else {
                    log.error(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
                }
            }
            patientEntityList.add(userEntity);
        }
    }
}


