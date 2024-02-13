package com.souzs.back.Repositores;

import com.souzs.back.Entites.CertificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CertificationStudentRepository extends JpaRepository<CertificationEntity, UUID> {
    @Query("SELECT c FROM certification c INNER JOIN c.studentEntity std WHERE std.email =:email AND c.tech =:tech")
    List<CertificationEntity> findByStudentEmailAndTech(String email, String tech);

    @Query("SELECT c FROM certification c WHERE c.tech =:tech ORDER BY c.grade DESC LIMIT 10")
    List<CertificationEntity> findTop10ByTech(String tech);
}
