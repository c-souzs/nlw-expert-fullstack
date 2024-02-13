package com.souzs.back.Service;

import com.souzs.back.DTOs.CheckStudentCertificationDTO;
import com.souzs.back.DTOs.StudentCertificationAnswerDTO;
import com.souzs.back.Entites.*;
import com.souzs.back.Repositores.CertificationStudentRepository;
import com.souzs.back.Repositores.QuestionRepository;
import com.souzs.back.Repositores.StudentRepository;
import com.souzs.back.Repositores.TechCertificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StudentCertificationAnswers {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    CertificationStudentRepository certificationStudentRepository;

    @Autowired
    private CheckHasCertification checkHasCertification;

    @Autowired
    TechCertificationRepository techCertificationRepository;

    public CertificationEntity execute(StudentCertificationAnswerDTO studentAnswerCertificationDTO) throws Exception {

        var hasCertification = this.checkHasCertification.execute(new CheckStudentCertificationDTO(studentAnswerCertificationDTO.getEmail(), studentAnswerCertificationDTO.getTechId()));

        if(hasCertification) throw new Exception("Estudante já possui uma certificação para essa tecnologia.");

        var idTech = studentAnswerCertificationDTO.getTechId();

        Optional<TechCertificationEntity> techCertification = techCertificationRepository.findById(idTech);

        if(techCertification.isEmpty()) throw new RuntimeException("Tecnologia não encontrada.");

        List<QuestionEntity> questionsByTech = questionRepository.findByTechEntityId(idTech);
        List<AnswerEntity> answersCertification = new ArrayList<>();

        AtomicInteger correctAnswers = new AtomicInteger(0);

        // Verifca se as respostas do estudante estão corretas
        studentAnswerCertificationDTO.getAnswersStudent().forEach(answer -> {
            var hasQuestionByAnswer = questionsByTech.stream().filter(question -> question.getId().equals(answer.getQuestionId())).findFirst();

            if(hasQuestionByAnswer.isEmpty()) throw new RuntimeException("Questão não encontrada.");

            var questionByAnswer = hasQuestionByAnswer.get();

            var hasFindCorrectAnswer = questionByAnswer.getAlternatives().stream().filter(alternative -> alternative.isCorrect()).findFirst();

            if(hasFindCorrectAnswer.isEmpty()) throw new RuntimeException("Alternativa correta não encontrada.");

            var correctAnswer = hasFindCorrectAnswer.get();

            if(correctAnswer.getId().equals(answer.getAlternativeId())) {
                correctAnswers.getAndIncrement();
                answer.setCorrect(true);
            } else {
                answer.setCorrect(false);
            }
        });

        var student = studentRepository.findByEmail(studentAnswerCertificationDTO.getEmail());
        UUID studentId;

        if(student.isEmpty()) {
            var createStudent = StudentEntity.builder().email(studentAnswerCertificationDTO.getEmail()).build();
            createStudent = studentRepository.save(createStudent);
            studentId = createStudent.getId();
        } else {
            studentId = student.get().getId();
        }

        CertificationEntity certification = CertificationEntity.builder()
                .techEntity(techCertification.get())
                .studentId(studentId)
                .grade(correctAnswers.get())
                .build();

        var createCertification = certificationStudentRepository.save(certification);

        answersCertification.stream().forEach(answerCertification -> {
            answerCertification.setCertificationId(createCertification.getId());
            answerCertification.setCertification(createCertification);
        });

        certification.setAnswers(answersCertification);

        certificationStudentRepository.save(certification);

        return createCertification;
    }

}
