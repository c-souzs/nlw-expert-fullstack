package com.souzs.back.Controllers;

import com.souzs.back.DTOs.AlternativeResultDTO;
import com.souzs.back.DTOs.QuestionResultDTO;
import com.souzs.back.Entites.AlternativeEntity;
import com.souzs.back.Entites.QuestionEntity;
import com.souzs.back.Repositores.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/tech/{tech}")
    public List<QuestionResultDTO> findByTech(@PathVariable String tech) {
        var result = this.questionRepository.findByTech(tech);
        System.out.println("RESULT " + result);
        var toMap = result.stream().map(question -> mapQuestionToDTO(question))
                .collect(Collectors.toList());
        return toMap;
    }

    static QuestionResultDTO mapQuestionToDTO(QuestionEntity question) {
        var questionResultDTO = QuestionResultDTO.builder()
                .id(question.getId())
                .technology(question.getTech())
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
