package com.example.github_repo_fetcher.controller;

import com.example.github_repo_fetcher.dto.RepositoryDto;
import com.example.github_repo_fetcher.exception.UserNotFoundException;
import com.example.github_repo_fetcher.response.ErrorResponse;
import com.example.github_repo_fetcher.service.GitHubService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/repositories")
public class GitHubController {

    private final GitHubService gitHubService;

    public GitHubController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserRepositories(@PathVariable String username) {
        try {
            List<RepositoryDto> repositories = gitHubService.getNonForkRepositories(username);
            return ResponseEntity.ok(repositories);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(404).body(new ErrorResponse(404, "User not found"));
        }
    }

}
