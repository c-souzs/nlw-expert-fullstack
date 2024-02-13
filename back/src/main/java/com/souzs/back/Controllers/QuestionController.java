package com.souzs.back.Controllers;

import com.souzs.back.DTOs.AlternativeResultDTO;
import com.souzs.back.DTOs.QuestionResultDTO;
import com.souzs.back.Entites.AlternativeEntity;
import com.souzs.back.Entites.QuestionEntity;
import com.souzs.back.Repositores.QuestionRepository;
import com.souzs.back.Repositores.TechCertificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private TechCertificationRepository techCertificationRepository;

    @GetMapping("/tech/{techId}")
    public ResponseEntity<Object> findByTech(@PathVariable UUID techId) {

        try {
            var resulTech = techCertificationRepository.findById(techId);

            if(resulTech.isEmpty()) throw new RuntimeException("Tecnhologia não encontrada.");

            var result = this.questionRepository.findByTechEntityId(resulTech.get().getId());

            var toMap = result.stream().map(question -> mapQuestionToDTO(question))
                    .collect(Collectors.toList());
            return ResponseEntity.ok().body(toMap);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    static QuestionResultDTO mapQuestionToDTO(QuestionEntity question) {
        var questionResultDTO = QuestionResultDTO.builder()
                .id(question.getId())
                .tech(question.getTechEntity())
                .description(question.getDescription()).build();

        List<AlternativeResultDTO> alternativesResultDTOs = question.getAlternatives()
                .stream().map(alternative -> mapAlternativeDTO(alternative))
                .collect(Collectors.toList());

        questionResultDTO.setAlternatives(alternativesResultDTOs);
        return questionResultDTO;
    }

    static AlternativeResultDTO mapAlternativeDTO(AlternativeEntity alternativesResultDTO) {
        return AlternativeResultDTO.builder()
                .id(alternativesResultDTO.getId())
                .description(alternativesResultDTO.getDescription()).build();
    }
}
