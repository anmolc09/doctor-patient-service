package com.learning.service;

import com.learning.entity.DoctorEntity;
import com.learning.entity.PatientEntity;
import com.learning.enums.ErrorMessages;
import com.learning.enums.InfoMessages;
import com.learning.exceptions.DataNotFoundException;
import com.learning.models.DoctorModel;
import com.learning.repository.DoctorRepository;
import com.learning.utility.converter.DoctorMapper;
import com.learning.utility.excel.reader.DoctorReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorReader doctorReader;

    public List<DoctorModel> getAllDoctorsByPaginationAndSorting(int page, int limit, String sortBy){
        return doctorRepository.findAll(PageRequest.of(page,limit, Sort.by(sortBy))).stream()
                .map(doctorEntity -> DoctorMapper.convertToModel(doctorEntity))
                .collect(Collectors.toList());
    }

    public List<DoctorModel> getAllDoctors() {
        return doctorRepository.findAll().stream()
                .map(doctorEntity -> DoctorMapper.convertToModel(doctorEntity))
                .collect(Collectors.toList());
    }

    public DoctorModel getDoctorById(Long id) {
        if (doctorRepository.existsById(id)) {
            DoctorEntity doctorEntity = doctorRepository.findById(id)
                    .orElseThrow(() -> new DataNotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()));
            return DoctorMapper.convertToModel(doctorEntity);
        }
        throw new DataNotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
    }

    public DoctorModel saveDoctor(DoctorModel doctor) {
        log.info(InfoMessages.SAVING_DATA_IN_JPA.getInfoMessage());
        doctorRepository.save(DoctorMapper.convertToEntity(doctor));
        return doctor;
    }

    public void deletePatientById(Long id) {
        doctorRepository.deleteById(id);
    }

    public void saveExcelFile(MultipartFile file) {
        if (file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            try {
                List<DoctorEntity> doctorEntityList = doctorReader.getDoctorsList(file.getInputStream());
                log.info(InfoMessages.SAVING_DATA_IN_JPA.getInfoMessage());
                doctorRepository.saveAll(doctorEntityList);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
//Todo: Pagination and sorting