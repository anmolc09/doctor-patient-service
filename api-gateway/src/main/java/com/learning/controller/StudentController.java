package com.learning.controller;

import com.learning.client.StudentClient;
import com.learning.models.StudentModel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentClient studentClient;

    @GetMapping("/{id}")
    public StudentModel getRecordById(@PathVariable Long id){
        return studentClient.getRecordById(id);
    }

    @GetMapping("/get-records")
    public List<StudentModel> getAllRecords(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
            @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy){
        return studentClient.getAllRecords(page, limit, sortBy);
    }

    @PostMapping
    public List<StudentModel> saveRecords(@RequestBody List<StudentModel> studentModelList){
        return studentClient.saveRecords(studentModelList);
    }

    @PutMapping("/{id}")
    public StudentModel updateRecordById(@PathVariable Long id, @RequestBody StudentModel studentModel){
        return studentClient.updateRecordById(id, studentModel);
    }

    @DeleteMapping("/{id}")
    public void deleteRecordById(@PathVariable Long id){
        studentClient.deleteRecordById(id);
    }

    @PostMapping("/upload")
    public void uploadExcelFile(@RequestParam("file") MultipartFile file){
        studentClient.uploadExcelFile(file);
    }

    @PostMapping("/email")
    public void emailSender(){
        studentClient.emailSender();
    }
    @PostMapping("/email/attachment")
    public void sendMailWithAttachment(){
        studentClient.sendMailWithAttachment();
    }

}
