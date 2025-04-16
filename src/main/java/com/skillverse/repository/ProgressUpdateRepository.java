package com.skillverse.repository;

import com.skillverse.model.ProgressUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProgressUpdateRepository extends JpaRepository<ProgressUpdate, Long> {
    List<ProgressUpdate> findByUserId(String userId);  // Changed from String to Long to match your model

    List<ProgressUpdate> findByUserId(Long userId);
}