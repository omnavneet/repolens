package com.repolens.backend.controller;

import com.repolens.backend.dto.RepoRequestDTO;
import com.repolens.backend.dto.RepoResponseDTO;
import com.repolens.backend.service.RepoAnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/repo-analysis")
public class RepoAnalysisController {

    private final RepoAnalysisService repoAnalysisService;

    @PostMapping("/analyze")
    public ResponseEntity<RepoResponseDTO> analyzeRepository(
            @RequestBody RepoRequestDTO request
    ) {
        RepoResponseDTO response = repoAnalysisService.analyzeRepository(request);

        return ResponseEntity.ok(response);
    }
}