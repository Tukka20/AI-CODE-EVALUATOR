package com.example.smartcodeevaluator.controller;

import com.example.smartcodeevaluator.service.LeetCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/problems")
public class ProblemController {
    @Autowired
    private LeetCodeService leetCodeService;

    @GetMapping("/{problemSlug}")
    public String getProblem(@PathVariable String problemSlug) {
        return leetCodeService.fetchProblem(problemSlug);
    }
}