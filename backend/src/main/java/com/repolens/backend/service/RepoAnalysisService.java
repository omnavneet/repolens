package com.repolens.backend.service;

import com.repolens.backend.dto.RepoRequestDTO;
import com.repolens.backend.dto.RepoResponseDTO;
import com.repolens.backend.dto.RepoScanResultDTO;
import com.repolens.backend.entity.RepoAnalysis;
import com.repolens.backend.repository.RepoAnalysisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;

@RequiredArgsConstructor
@Service
public class RepoAnalysisService {

    private final GitCloneService gitCloneService;
    private final FileScannerService fileScannerService;
    private final RepoAnalysisRepository repoAnalysisRepository;

    public RepoResponseDTO analyzeRepository(RepoRequestDTO request) {

        // 1) validate input
        String repoUrl = request.getRepoUrl();
        if (repoUrl == null || repoUrl.isBlank()) {
            throw new IllegalArgumentException("repoUrl is required");
        }

        // 2) clone repo
        File localRepo = gitCloneService.cloneRepo(repoUrl);

        // 3) scan files and folders
        RepoScanResultDTO scan = fileScannerService.scan(localRepo);

        // 4) build response
        RepoResponseDTO response = new RepoResponseDTO();
        response.setProjectName(scan.getProjectName());
        response.setRepoUrl(repoUrl);
        response.setTotalFiles(scan.getTotalFiles());
        response.setTotalFolders(scan.getTotalFolders());
        response.setFolders(scan.getFolders());
        response.setLanguages(scan.getLanguages());

         repoAnalysisRepository.save(entityFrom(response));

        return response;
    }

    private RepoAnalysis entityFrom(RepoResponseDTO response) {
        RepoAnalysis entity = new RepoAnalysis();
        entity.setProjectName(response.getProjectName());
        entity.setRepoUrl(response.getRepoUrl());
        entity.setTotalFiles(response.getTotalFiles());
        entity.setTotalFolders(response.getTotalFolders());
        entity.setFolders(response.getFolders());
        entity.setLanguages(response.getLanguages());
        return entity;
    }
}