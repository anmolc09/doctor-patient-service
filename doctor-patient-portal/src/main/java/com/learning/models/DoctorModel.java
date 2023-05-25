package com.learning.models;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Builder
public class DoctorModel {

    private Long id;
    private String name;
    private String specialization;
    private String email;
    private String phone;
    private String address;
}
