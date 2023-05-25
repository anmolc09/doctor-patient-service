package com.learning.service;

import com.learning.entity.PatientEntity;
import com.learning.enums.ErrorMessages;
import com.learning.enums.InfoMessages;
import com.learning.exceptions.DataNotFoundException;
import com.learning.models.PatientModel;
import com.learning.repository.PatientRepository;
import com.learning.utility.converter.PatientMapper;
import com.learning.utility.excel.reader.PatientReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientReader patientReader;


    public List<PatientModel> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(patientEntity -> PatientMapper.convertToModel(patientEntity))
                .collect(Collectors.toList());
    }

    public PatientModel getPatientById(Long id) {
        if (patientRepository.existsById(id)) {
            PatientEntity patientEntity = patientRepository.findById(id)
                    .orElseThrow(() -> new DataNotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()));
            return PatientMapper.convertToModel(patientEntity);
        }
        throw new DataNotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
    }

    public PatientModel savePatient(PatientModel patient) {
        log.info(InfoMessages.SAVING_DATA_IN_JPA.getInfoMessage());
        patientRepository.save(PatientMapper.convertToEntity(patient));
        return patient;
    }

    //updatePatientById
    public void deletePatientById(Long id) {
        patientRepository.deleteById(id);
    }


    public void saveExcelFile(MultipartFile file) {
        if (file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            try {
                List<PatientEntity> patientEntityList = patientReader.getPatientList(file.getInputStream());
                log.info(InfoMessages.SAVING_DATA_IN_JPA.getInfoMessage());
                patientRepository.saveAll(patientEntityList);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
