package com.learning.service;

import com.learning.client.EmailClient;
import com.learning.entity.AppointmentEntity;
import com.learning.constants.EmailConstants;
import com.learning.enums.ErrorMessages;
import com.learning.enums.InfoMessages;
import com.learning.exceptions.DataNotFoundException;
import com.learning.models.AppointmentModel;
import com.learning.repository.AppointmentRepository;
import com.learning.shared.EmailRequest;
import com.learning.utility.converter.AppointmentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final EmailClient emailClient;
    private final EmailRequest emailRequest;

    public List<AppointmentModel> getAllAppointments() {
        return appointmentRepository.findAll().stream()
                .map(appointmentEntity -> AppointmentMapper.convertToModel(appointmentEntity))
                .collect(Collectors.toList());
    }

    public AppointmentModel getAppointmentById(Long id) {
        if (appointmentRepository.existsById(id)) {
            AppointmentEntity appointmentEntity = appointmentRepository.findById(id)
                    .orElseThrow(() -> new DataNotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()));
            return AppointmentMapper.convertToModel(appointmentEntity);
        }
        throw new DataNotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
    }

    public AppointmentModel createAppointment(AppointmentModel appointment) {
        AppointmentEntity appointmentEntity = AppointmentMapper.convertToEntity(appointment);
        log.info(InfoMessages.SAVING_DATA_IN_JPA.getInfoMessage());
        appointmentRepository.save(appointmentEntity);
        CompletableFuture.runAsync(() -> sendAppointmentConfirmationEmail(appointment));
        return appointment;
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
        log.info(InfoMessages.DELETING_DATA_IN_JPA.getInfoMessage());
    }

    public void sendAppointmentConfirmationEmail(AppointmentModel appointmentModel) {

        String doctorEmail = appointmentModel.getDoctor().getEmail();
        String doctorMessage = EmailConstants.DOCTOR_MESSAGE.getEmailMessage() + appointmentModel.getPatient().getName()
                + EmailConstants.CONFIRMED_ON.getEmailMessage() + appointmentModel.getAppointmentDate();

        String patientEmail = appointmentModel.getPatient().getEmail();
        String patientMessage = EmailConstants.PATIENT_MESSAGE.getEmailMessage() + appointmentModel.getDoctor().getName()
                + EmailConstants.CONFIRMED_ON.getEmailMessage() + appointmentModel.getAppointmentDate();

        emailRequest.setSubject(EmailConstants.SUBJECT.getEmailMessage());

        emailRequest.setRecipient(doctorEmail);
        emailRequest.setMessage(doctorMessage);
        emailClient.sendEmails(emailRequest);


        emailRequest.setRecipient(patientEmail);
        emailRequest.setMessage(patientMessage);
        emailClient.sendEmails(emailRequest);
    }
}
