package com.example.github_repo_fetcher.dto;

public class BranchDto {

    private String name;
    private String lastCommitSha;

    public BranchDto(String name, String lastCommitSha) {
        this.name = name;
        this.lastCommitSha = lastCommitSha;
    }

    public String getName() {
        return name;
    }

    public String getLastCommitSha() {
        return lastCommitSha;
    }
}
