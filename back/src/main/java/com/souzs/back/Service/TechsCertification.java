package com.souzs.back.Service;

import com.souzs.back.Entites.TechCertificationEntity;
import com.souzs.back.Repositores.TechCertificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechsCertification {

    @Autowired
    TechCertificationRepository techCertificationRepository;

    public List<TechCertificationEntity> execute() {
        return techCertificationRepository.findAll();
    }
}
