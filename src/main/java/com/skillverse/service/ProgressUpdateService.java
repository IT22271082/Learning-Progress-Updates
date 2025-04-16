package com.skillverse.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillverse.exception.ResourceNotFoundException;
import com.skillverse.model.Certificate;
import com.skillverse.model.ProgressTemplate;
import com.skillverse.model.ProgressUpdate;
import com.skillverse.model.User;
import com.skillverse.repository.CertificateRepository;
import com.skillverse.repository.ProgressUpdateRepository;
import com.skillverse.repository.UserRepository;

@Service
public class ProgressUpdateService {
    
    @Autowired
    private ProgressUpdateRepository progressUpdateRepository;
    
    @Autowired
    private CertificateRepository certificateRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public ProgressUpdate createUpdate(ProgressUpdate update, Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        update.setUser(user);
        update.setCreatedAt(LocalDateTime.now());
        return progressUpdateRepository.save(update);
    }
    
    public List<ProgressUpdate> getUserUpdates(Long userId) {
        return progressUpdateRepository.findByUserId(userId);
    }
    
    public ProgressUpdate updateProgress(Long updateId, ProgressUpdate updateDetails) {
        ProgressUpdate update = progressUpdateRepository.findById(updateId)
            .orElseThrow(() -> new ResourceNotFoundException("Progress update not found with id: " + updateId));
        
        update.setTitle(updateDetails.getTitle());
        update.setContent(updateDetails.getContent());
        update.setTemplate(updateDetails.getTemplate());
        update.setUpdatedAt(LocalDateTime.now());
        
        return progressUpdateRepository.save(update);
    }
    
    public void deleteUpdate(Long updateId) {
        ProgressUpdate update = progressUpdateRepository.findById(updateId)
            .orElseThrow(() -> new ResourceNotFoundException("Progress update not found with id: " + updateId));
        progressUpdateRepository.delete(update);
    }
    
    public List<String> getAvailableTemplates() {
        return Arrays.stream(ProgressTemplate.values())
            .map(Enum::name)
            .collect(Collectors.toList());
    }
    
    public Certificate generateCertificate(Long userId, String achievement) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
            
        Certificate certificate = new Certificate();
        certificate.setUser(user);
        certificate.setAchievement(achievement);
        certificate.setIssuedDate(LocalDate.now());
        certificate.setCertificateUrl(generateCertificateUrl());
        
        return certificateRepository.save(certificate);
    }
    
    private String generateCertificateUrl() {
        return "https://api.yourplatform.com/certificates/" + UUID.randomUUID();
    }
}