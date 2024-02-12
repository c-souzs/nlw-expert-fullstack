package com.souzs.back.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckStudentCertificationDTO {
    private String email;
    private String tech;
}
