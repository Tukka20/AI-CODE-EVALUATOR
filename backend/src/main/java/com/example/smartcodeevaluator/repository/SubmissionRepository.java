package com.example.smartcodeevaluator.repository;

import com.example.smartcodeevaluator.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionRepository extends JpaRepository<Submission, Integer> {
}