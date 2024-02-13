package com.souzs.back.Repositores;

import com.souzs.back.Entites.CertificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CertificationStudentRepository extends JpaRepository<CertificationEntity, UUID> {
    @Query("SELECT c FROM certification c WHERE c.studentEntity.email = :email AND c.techEntity.id = :techId")
    List<CertificationEntity> findByStudentEmailAndTech(String email, UUID techId);

    @Query("SELECT c FROM certification c WHERE c.techEntity.id =:techId ORDER BY c.grade DESC LIMIT 10")
    List<CertificationEntity> findTop10ByTech(UUID techId);
}
