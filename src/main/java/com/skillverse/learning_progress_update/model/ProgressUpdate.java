package com.skillverse.learning_progress_update.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ProgressUpdate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String content;
    private String templateType;
    private LocalDateTime createdAt;
    public void setCreatedAt(LocalDateTime now) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCreatedAt'");
    }

    // Getters & Setters (generate these in VS Code with Right-click → Source Action)
}