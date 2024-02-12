package com.souzs.back.Controllers;

import com.souzs.back.DTOs.CheckStudentCertificationDTO;
import com.souzs.back.Service.CheckHasCertification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private CheckHasCertification checkHasCertification;

    @PostMapping("/checkHasCertificate")
    public String checkHasCertificate(@RequestBody CheckStudentCertificationDTO checkStudentCertificationDTO) {
        var result = this.checkHasCertification.execute(checkStudentCertificationDTO);

        if(result) {
            return "Student has certificate";
        }

        return "Student does not have certificate";
    }
}
