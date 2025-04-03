package com.example.github_repo_fetcher.dto;

import com.example.github_repo_fetcher.model.Branch;
import java.util.List;

public class RepositoryDto {

    private String repositoryName;
    private String ownerLogin;
    private List<BranchDto> branches;

    public RepositoryDto(String repositoryName, String ownerLogin, List<BranchDto> branches) {
        this.repositoryName = repositoryName;
        this.ownerLogin = ownerLogin;
        this.branches = branches;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public String getOwnerLogin() {
        return ownerLogin;
    }

    public List<BranchDto> getBranches() {
        return branches;
    }
}
