package com.skillverse.controller;

import com.skillverse.learning_progress_update.model.ProgressUpdate;
import com.skillverse.repository.ProgressUpdateRepository;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/progress-updates")
public class ProgressUpdateController {
    @Autowired
    private ProgressUpdateRepository repository;

    @PostMapping
    public ProgressUpdate createUpdate(@RequestBody ProgressUpdate update) {
        update.setCreatedAt(LocalDateTime.now());
        return repository.save(update);
    }
}
