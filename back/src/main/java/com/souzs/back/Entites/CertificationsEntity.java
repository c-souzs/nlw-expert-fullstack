package com.souzs.back.Entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificationsEntity {
    private UUID studentId;
    private UUID id;
    private String tech;
    private int grate;
    private List<AnswersEntity> answers;
}
