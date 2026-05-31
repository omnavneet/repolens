package com.repolens.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class RepoAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String projectName;

    private String repoUrl;

    private int totalFiles;

    private int totalFolders;

    @ElementCollection
    private List<String> folders;

    @ElementCollection
    private List<String> languages;

}