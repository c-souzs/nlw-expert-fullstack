package com.souzs.back.Service;

import com.souzs.back.Entites.CertificationEntity;
import com.souzs.back.Repositores.CertificationStudentRepository;
import com.souzs.back.Repositores.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Student {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CertificationStudentRepository certificationStudentRepository;

    public List<CertificationEntity> execute(String email) throws Exception {
        var resultEmail = studentRepository.findByEmail(email);

        if(resultEmail.isEmpty()) throw new RuntimeException("Estudante não encontrado.");

        return certificationStudentRepository.findByStudentId(resultEmail.get().getId());
    }
}
