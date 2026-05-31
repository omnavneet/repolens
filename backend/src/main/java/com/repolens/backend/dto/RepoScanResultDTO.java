package com.repolens.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RepoScanResultDTO {

    private String projectName;
    private int totalFiles;
    private int totalFolders;
    private List<String> folders;
    private List<String> languages;

}
