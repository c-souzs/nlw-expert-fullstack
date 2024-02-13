package com.souzs.back.Controllers;

import com.souzs.back.DTOs.CheckStudentCertificationDTO;
import com.souzs.back.DTOs.StudentCertificationAnswerDTO;
import com.souzs.back.Entites.CertificationEntity;
import com.souzs.back.Service.CheckHasCertification;
import com.souzs.back.Service.StudentCertificationAnswers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private CheckHasCertification checkHasCertification;

    @Autowired
    private StudentCertificationAnswers studentCertificationAnswers;

    @PostMapping("/checkHasCertificate")
    public String checkHasCertificate(@RequestBody CheckStudentCertificationDTO checkStudentCertificationDTO) {
        var result = this.checkHasCertification.execute(checkStudentCertificationDTO);

        if(result) {
            return "Student has certificate";
        }

        return "Student does not have certificate";
    }

    @PostMapping("/certificationAnswer")
    public ResponseEntity<Object> certificationAnswer(@RequestBody StudentCertificationAnswerDTO studentCertificationAnswerDTO) {

        try {
            var result = this.studentCertificationAnswers.execute(studentCertificationAnswerDTO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
