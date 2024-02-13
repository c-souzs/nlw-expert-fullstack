package com.souzs.back.Controllers;

import com.souzs.back.DTOs.CheckStudentCertificationDTO;
import com.souzs.back.DTOs.StudentCertificationAnswerDTO;
import com.souzs.back.Service.CheckHasCertification;
import com.souzs.back.Service.Student;
import com.souzs.back.Service.StudentCertification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/students")
public class StudentController {

    @Autowired
    private CheckHasCertification checkHasCertification;

    @Autowired
    private StudentCertification studentCertification;

    @Autowired
    private Student student;

    @PostMapping("/checkHasCertificate")
    public String checkHasCertificate(@RequestBody CheckStudentCertificationDTO checkStudentCertificationDTO) {
        var result = this.checkHasCertification.execute(checkStudentCertificationDTO);

        if(result) return "Student has certificate";

        return "Student does not have certificate";
    }

    @PostMapping("/certificationAnswer")
    public ResponseEntity<Object> certificationAnswer(@RequestBody StudentCertificationAnswerDTO studentCertificationAnswerDTO) {

        try {
            var result = this.studentCertification.execute(studentCertificationAnswerDTO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/certificationsStudent/{email}")
    public ResponseEntity<Object> certificationsStudent(@PathVariable String email) {
        try {
            var result = this.student.execute(email);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
