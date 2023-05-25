package com.learning.rest;

import com.learning.models.DoctorModel;
import com.learning.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping("/limited")
    public List<DoctorModel> getAllDoctorsByPaginationAndSorting(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
            @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy) {
        return doctorService.getAllDoctorsByPaginationAndSorting(page, limit, sortBy);
    }

    @GetMapping("/all")
    public List<DoctorModel> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{id}")
    public DoctorModel getDoctorById(@PathVariable Long id) {
        return doctorService.getDoctorById(id);
    }

    @PostMapping
    public DoctorModel saveDoctor(@RequestBody DoctorModel doctor) {
        return doctorService.saveDoctor(doctor);
    }

    @DeleteMapping("/{id}")
    public void deletePatientById(@PathVariable Long id) {
        doctorService.deletePatientById(id);
    }

    @PostMapping("/upload")
    public void uploadExcelFile(@RequestParam("file") MultipartFile file) {
        doctorService.saveExcelFile(file);
    }

}
