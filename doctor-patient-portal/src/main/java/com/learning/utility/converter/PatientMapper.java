package com.learning.utility.converter;

import com.learning.entity.PatientEntity;
import com.learning.models.PatientModel;
import org.springframework.stereotype.Service;

@Service
public class PatientMapper {

    public static PatientModel convertToModel(PatientEntity patient) {
        return PatientModel.builder()
                .id(patient.getId())
                .name(patient.getName())
                .address(patient.getAddress())
                .email(patient.getEmail())
                .phone(patient.getPhone())
                .gender(patient.getGender())
                .dateOfBirth(patient.getDateOfBirth())
                .build();
    }

    public static PatientEntity convertToEntity(PatientModel patient) {
        return PatientEntity.builder()
                .id(patient.getId())
                .name(patient.getName())
                .address(patient.getAddress())
                .email(patient.getEmail())
                .phone(patient.getPhone())
                .gender(patient.getGender())
                .dateOfBirth(patient.getDateOfBirth())
                .build();
    }


}
