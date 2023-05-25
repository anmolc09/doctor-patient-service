package com.learning.client;

import com.learning.models.StudentModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient(value = "studentClient", url = "$(student-service)")
public interface StudentClient {

    @GetMapping("/{id}")
    StudentModel getRecordById(@PathVariable Long id);

    @GetMapping("/get-records")
    List<StudentModel> getAllRecords(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
            @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy);

    @PostMapping
    List<StudentModel> saveRecords(@RequestBody List<StudentModel> studentModelList);

    @PutMapping("/{id}")
    StudentModel updateRecordById(@PathVariable Long id, @RequestBody StudentModel studentModel);

    @DeleteMapping("/{id}")
    void deleteRecordById(@PathVariable Long id);

    @PostMapping("/upload")
    void uploadExcelFile(@RequestParam("file") MultipartFile file);

    @PostMapping("/email")
    void emailSender();

    @PostMapping("/email/attachment")
    void sendMailWithAttachment();

}
