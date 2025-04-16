package com.skillverse.repository;

import com.skillverse.model.Certificate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    List<Certificate> findByUserId(Long userId);  // Optional: Add if you need to query by user
}