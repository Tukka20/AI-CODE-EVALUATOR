package com.example.smartcodeevaluator.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "submissions")
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer submissionId;
    private Integer userId;
    private Integer problemId;
    @Column(columnDefinition = "TEXT")
    private String code;
    private String language;
    private String status;
    private Double executionTime;
    @Column(columnDefinition = "TEXT")
    private String plagiarismReport;
    @Column(columnDefinition = "TEXT")
    private String aiFeedback;

    // Getters and setters
    public Integer getSubmissionId() { return submissionId; }
    public void setSubmissionId(Integer submissionId) { this.submissionId = submissionId; }
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public Integer getProblemId() { return problemId; }
    public void setProblemId(Integer problemId) { this.problemId = problemId; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Double getExecutionTime() { return executionTime; }
    public void setExecutionTime(Double executionTime) { this.executionTime = executionTime; }
    public String getPlagiarismReport() { return plagiarismReport; }
    public void setPlagiarismReport(String plagiarismReport) { this.plagiarismReport = plagiarismReport; }
    public String getAiFeedback() { return aiFeedback; }
    public void setAiFeedback(String aiFeedback) { this.aiFeedback = aiFeedback; }
}