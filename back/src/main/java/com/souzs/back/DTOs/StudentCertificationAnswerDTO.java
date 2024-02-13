package com.souzs.back.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentCertificationAnswerDTO {
    private String email;
    private String tech;
    private List<QuestionAnswerDTO> answersStudent;
}
