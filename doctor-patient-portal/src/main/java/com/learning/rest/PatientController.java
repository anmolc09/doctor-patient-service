package com.learning.rest;

import com.learning.models.PatientModel;
import com.learning.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/all")
    public List<PatientModel> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public PatientModel getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    @PostMapping
    public PatientModel savePatient(@RequestBody PatientModel patient) {
        return patientService.savePatient(patient);
    }

    //updatePatientById
    @DeleteMapping("/{id}")
    public void deletePatientById(@PathVariable Long id) {
        patientService.deletePatientById(id);
    }
    @PostMapping("/upload")
    public void uploadExcelFile(@RequestParam("file") MultipartFile file) {
        patientService.saveExcelFile(file);
    }
}
