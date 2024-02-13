package com.souzs.back.Service;

import com.souzs.back.DTOs.CheckStudentCertificationDTO;
import com.souzs.back.Repositores.CertificationStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckHasCertification {
    @Autowired
    private CertificationStudentRepository certificationStudentRepository;

    public boolean execute(CheckStudentCertificationDTO studentDTO) {
        var result = this.certificationStudentRepository.findByStudentEmailAndTech(studentDTO.getEmail(), studentDTO.getTechId());

        return !result.isEmpty();
    }
}
