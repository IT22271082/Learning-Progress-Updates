package com.skillverse.controller;

import com.skillverse.model.ProgressUpdate;
import com.skillverse.repository.ProgressUpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/progress-updates")
public class ProgressUpdateController {

    @Autowired
    private ProgressUpdateRepository repository;

    // Test endpoint
    @GetMapping("/test")
    public String testEndpoint() {
        return "API is working!";
    }

    // Create
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProgressUpdate createUpdate(@RequestBody ProgressUpdate update) {
        update.setCreatedAt(LocalDateTime.now());
        return repository.save(update);
    }

    // Read (all for user)
    @GetMapping("/{userId}")
    public List<ProgressUpdate> getUpdatesByUser(@PathVariable String userId) {
        return repository.findByUserId(userId);
    }

    // Update
    @PutMapping("/{id}")
    public ProgressUpdate updateUpdate(
            @PathVariable Long id,
            @RequestBody ProgressUpdate updateDetails) {
        return repository.findById(id)
            .map(update -> {
                update.setContent(updateDetails.getContent());
                update.setTemplate(updateDetails.getTemplate());
                return repository.save(update);
            })
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "ProgressUpdate not found"));
    }

    // Delete
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUpdate(@PathVariable Long id) {
        repository.deleteById(id);
    }

    // Certificate generation
    @GetMapping("/certificates/{userId}")
    public ResponseEntity<String> generateCertificate(@PathVariable String userId) {
        return ResponseEntity.ok("Certificate generated for user: " + userId);
    }
}