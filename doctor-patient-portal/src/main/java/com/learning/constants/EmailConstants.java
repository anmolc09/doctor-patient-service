package com.learning.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EmailConstants {
    DOCTOR_MESSAGE("Appointment with patient "),
    CONFIRMED_ON(" confirmed on "),
    PATIENT_MESSAGE("Appointment with doctor "),
    SUBJECT("APPOINTMENT CONFIRMED");
    private final String emailMessage;
}
