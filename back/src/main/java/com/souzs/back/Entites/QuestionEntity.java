package com.souzs.back.Entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "question")
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String description;

    @ManyToOne
    @JoinColumn(name = "tech_id")
    private TechCertificationEntity techEntity;

    @OneToMany
    @JoinColumn(name = "question_id")
    private List<AlternativeEntity> alternatives;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
