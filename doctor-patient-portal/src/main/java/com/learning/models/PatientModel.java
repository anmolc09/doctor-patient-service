package com.learning.models;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class PatientModel {

    private Long id;
    private String name;
    private String gender;
    private LocalDate dateOfBirth;
    private String email;
    private String phone;
    private String address;
}
