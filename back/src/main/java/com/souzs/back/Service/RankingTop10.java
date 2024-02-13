package com.souzs.back.Service;

import com.souzs.back.Entites.CertificationEntity;
import com.souzs.back.Repositores.CertificationStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RankingTop10 {

    @Autowired
    private CertificationStudentRepository certificationStudentRepository;

    public List<CertificationEntity> execute(UUID techId) {
        var result = this.certificationStudentRepository.findTop10ByTech(techId);

        if(result.isEmpty()) throw new RuntimeException("Não existe certificações para essa tecnologia.");

        return result;
    }
}
