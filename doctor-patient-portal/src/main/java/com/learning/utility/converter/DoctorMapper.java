package com.learning.utility.converter;

import com.learning.entity.DoctorEntity;
import com.learning.models.DoctorModel;
import org.springframework.stereotype.Service;

@Service
public class DoctorMapper {

    public static DoctorModel convertToModel(DoctorEntity doctorEntity) {
        return DoctorModel.builder()
                .id(doctorEntity.getId())
                .name(doctorEntity.getName())
                .email(doctorEntity.getEmail())
                .address(doctorEntity.getAddress())
                .phone(doctorEntity.getPhone())
                .specialization(doctorEntity.getSpecialization())
                .build();
    }

    public static DoctorEntity convertToEntity(DoctorModel doctorModel) {
        return DoctorEntity.builder()
                .id(doctorModel.getId())
                .name(doctorModel.getName())
                .email(doctorModel.getEmail())
                .address(doctorModel.getAddress())
                .phone(doctorModel.getPhone())
                .specialization(doctorModel.getSpecialization())
                .build();
    }

}
