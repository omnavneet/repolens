package com.repolens.backend.service;

import com.repolens.backend.dto.RepoRequestDTO;
import com.repolens.backend.dto.RepoResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class RepoAnalysisService {

    public RepoResponseDTO analyzeRepository(
            RepoRequestDTO request
    ) {

        RepoResponseDTO response = new RepoResponseDTO();

        // clone repo
        // scan files
        // save analysis
        // return response

        return response;
    }
}