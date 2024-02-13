package com.souzs.back.Controllers;

import com.souzs.back.Entites.CertificationEntity;
import com.souzs.back.Service.RankingTop10;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ranking")
public class RankingController {

    @Autowired
    private RankingTop10 rankingTop10;

    @GetMapping("/{tech}")
    public ResponseEntity<Object> getRanking(@PathVariable String tech) {

        try {
            var result = this.rankingTop10.execute(tech);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
