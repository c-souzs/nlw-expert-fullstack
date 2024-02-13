package com.souzs.back.Service;

import com.souzs.back.Entites.CertificationEntity;
import com.souzs.back.Repositores.CertificationStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankingTop10 {

    @Autowired
    private CertificationStudentRepository certificationStudentRepository;

    public List<CertificationEntity> execute(String tech) {
        var result = this.certificationStudentRepository.findTop10ByTech(tech);

        if(result.isEmpty()) {
            throw new RuntimeException("Não existe certificações para essa tecnologia.");
        }

        return result;
    }
}
