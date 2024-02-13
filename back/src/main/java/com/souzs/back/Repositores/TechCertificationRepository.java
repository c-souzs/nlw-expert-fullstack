package com.souzs.back.Repositores;

import com.souzs.back.Entites.TechCertificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TechCertificationRepository extends JpaRepository<TechCertificationEntity, UUID> {
    List<TechCertificationEntity> findAll();

    Optional<TechCertificationEntity> findById(UUID techId);

    Optional<TechCertificationEntity> findByName(String name);
}
