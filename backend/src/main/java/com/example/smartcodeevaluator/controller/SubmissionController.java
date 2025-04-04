package com.example.smartcodeevaluator.controller;

import com.example.smartcodeevaluator.entity.Submission;
import com.example.smartcodeevaluator.repository.SubmissionRepository;
import com.example.smartcodeevaluator.service.CodeExecutionService;
import com.example.smartcodeevaluator.service.DeepSeekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {
    @Autowired
    private SubmissionRepository submissionRepository;
    @Autowired
    private CodeExecutionService codeExecutionService;
    @Autowired
    private DeepSeekService deepSeekService;

    @PostMapping
    public Submission submitCode(@RequestBody Submission submission) throws Exception {
        String output = codeExecutionService.executeCode(submission.getCode(), submission.getLanguage());
        submission.setStatus(output.contains("failed") ? "fail" : "pass");
        submission.setExecutionTime(0.02); // Placeholder

        String plagiarismResult = deepSeekService.checkPlagiarism(submission.getCode());
        submission.setPlagiarismReport(plagiarismResult);

        String aiFeedback = deepSeekService.getAiFeedback(submission.getCode());
        submission.setAiFeedback(aiFeedback);

        return submissionRepository.save(submission);
    }
}