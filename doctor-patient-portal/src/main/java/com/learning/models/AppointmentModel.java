package com.learning.models;

import com.learning.entity.DoctorEntity;
import com.learning.entity.PatientEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@Builder
public class AppointmentModel {
    private Long appointmentId;
    private DoctorEntity doctor;
    private PatientEntity patient;
    private LocalDateTime appointmentDate;
    private String status;
    private String notes;
    private String prescription;
}
