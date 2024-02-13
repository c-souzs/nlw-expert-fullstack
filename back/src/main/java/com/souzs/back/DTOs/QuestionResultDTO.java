package com.souzs.back.DTOs;

import com.souzs.back.Entites.TechCertificationEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionResultDTO {
    private UUID id;
    private TechCertificationEntity tech;
    private String description;

    private List<AlternativeResultDTO> alternatives;
}
