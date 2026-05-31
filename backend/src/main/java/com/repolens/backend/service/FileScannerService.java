package com.repolens.backend.service;

import com.repolens.backend.dto.RepoScanResultDTO;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Stream;

@Service
public class FileScannerService {

    public RepoScanResultDTO scan(File repoDir) {

        RepoScanResultDTO result = new RepoScanResultDTO();
        result.setProjectName(repoDir.getName());

        Set<String> folderNames = new HashSet<>();
        Map<String, Integer> extCounts = new HashMap<>();
        int fileCount = 0;
        int folderCount = 0;

        try (Stream<java.nio.file.Path> paths = Files.walk(repoDir.toPath())) {
            for (java.nio.file.Path path : (Iterable<java.nio.file.Path>) paths::iterator) {
                if (Files.isDirectory(path)) {
                    folderCount++;
                    folderNames.add(path.getFileName().toString());
                } else {
                    fileCount++;
                    String name = path.getFileName().toString();
                    int dot = name.lastIndexOf('.');
                    if (dot > 0 && dot < name.length() - 1) {
                        String ext = name.substring(dot + 1).toLowerCase();
                        extCounts.put(ext, extCounts.getOrDefault(ext, 0) + 1);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to scan repo", e);
        }

        result.setTotalFiles(fileCount);
        result.setTotalFolders(folderCount);
        result.setFolders(new ArrayList<>(folderNames));
        result.setLanguages(mapExtToLanguages(extCounts.keySet()));

        return result;
    }

    private List<String> mapExtToLanguages(Set<String> extensions) {
        List<String> languages = new ArrayList<>();
        if (extensions.contains("java")) languages.add("Java");
        if (extensions.contains("js")) languages.add("JavaScript");
        if (extensions.contains("ts")) languages.add("TypeScript");
        if (extensions.contains("py")) languages.add("Python");
        return languages;
    }
}