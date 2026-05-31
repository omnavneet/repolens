package com.repolens.backend.service;

import org.eclipse.jgit.api.Git;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;

@Service
public class GitCloneService {

    public File cloneRepo(String repoUrl) {
        try {

            File tempDir = Files.createTempDirectory("repolens-").toFile();

            Git.cloneRepository()
                    .setURI(repoUrl)
                    .setDirectory(tempDir)
                    .call();

            return tempDir;

        } catch (Exception e) {
            throw new RuntimeException("Failed to clone repo", e);
        }
    }
}