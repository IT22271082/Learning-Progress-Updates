package com.skillverse.repository;

import com.skillverse.learning_progress_update.model.ProgressUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProgressUpdateRepository extends JpaRepository<ProgressUpdate, Long> {
    List<ProgressUpdate> findByUserId(String userId);
}