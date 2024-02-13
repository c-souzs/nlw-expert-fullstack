package com.souzs.back.DTOs;

import com.souzs.back.Entites.TechCertificationEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckStudentCertificationDTO {
    private String email;
    private UUID techId;
}
