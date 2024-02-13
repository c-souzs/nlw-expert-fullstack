package com.souzs.back.Controllers;

import com.souzs.back.Entites.CertificationEntity;
import com.souzs.back.Repositores.TechCertificationRepository;
import com.souzs.back.Service.RankingTop10;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/ranking")
public class RankingController {

    @Autowired
    private RankingTop10 rankingTop10;

    @Autowired
    private TechCertificationRepository techCertificationRepository;

    @GetMapping("/{techId}")
    public ResponseEntity<Object> getRanking(@PathVariable UUID techId) {
        try {

            var tech = this.techCertificationRepository.findById(techId);

            if(tech.isEmpty()) throw new RuntimeException("Tecnologia não encontrada.");


            var result = this.rankingTop10.execute(tech.get().getId());
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
