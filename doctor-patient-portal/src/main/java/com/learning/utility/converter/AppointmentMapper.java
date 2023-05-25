package com.learning.utility.converter;

import com.learning.entity.AppointmentEntity;
import com.learning.models.AppointmentModel;
import org.springframework.stereotype.Service;

@Service
public class AppointmentMapper {

    public static AppointmentModel convertToModel(AppointmentEntity appointmentEntity) {
        return AppointmentModel.builder()
                .appointmentId(appointmentEntity.getId())
                .appointmentDate(appointmentEntity.getAppointmentDate())
                .doctor(appointmentEntity.getDoctor())
                .patient(appointmentEntity.getPatient())
                .appointmentDate(appointmentEntity.getAppointmentDate())
                .notes(appointmentEntity.getNotes())
                .status(appointmentEntity.getStatus())
                .prescription(appointmentEntity.getPrescription())
                .build();
    }

    public static AppointmentEntity convertToEntity(AppointmentModel appointmentModel) {
        return AppointmentEntity.builder()
                .id(appointmentModel.getAppointmentId())
                .appointmentDate(appointmentModel.getAppointmentDate())
                .doctor(appointmentModel.getDoctor())
                .patient(appointmentModel.getPatient())
                .appointmentDate(appointmentModel.getAppointmentDate())
                .notes(appointmentModel.getNotes())
                .status(appointmentModel.getStatus())
                .prescription(appointmentModel.getPrescription())
                .build();
    }

}
