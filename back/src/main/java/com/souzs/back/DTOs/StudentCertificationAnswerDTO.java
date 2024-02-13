package com.souzs.back.DTOs;

import com.souzs.back.Entites.TechCertificationEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentCertificationAnswerDTO {
    private String email;
    private UUID techId;
    private List<QuestionAnswerDTO> answersStudent;
}
