package com.example.github_repo_fetcher.service;

import com.example.github_repo_fetcher.dto.BranchDto;
import com.example.github_repo_fetcher.dto.RepositoryDto;
import com.example.github_repo_fetcher.exception.UserNotFoundException;
import com.example.github_repo_fetcher.model.Branch;
import com.example.github_repo_fetcher.model.Repository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GitHubService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String GITHUB_API = "https://api.github.com/";

    public List<RepositoryDto> getNonForkRepositories(String username) {
        String url = GITHUB_API + "users/" + username + "/repos";

        try {
            ResponseEntity<Repository[]> response = restTemplate.getForEntity(url, Repository[].class);

            if (response.getBody() == null) {
                return List.of();
            }

            return List.of(response.getBody()).stream()
                    .filter(repo -> !repo.isFork())
                    .map(repo -> new RepositoryDto(
                            repo.getName(),
                            repo.getOwner().getLogin(),
                            getBranchesForRepository(username, repo.getName())
                    ))
                    .collect(Collectors.toList());

        } catch (HttpClientErrorException.NotFound e) {
            throw new UserNotFoundException("User not found");
        }
    }

    private List<BranchDto> getBranchesForRepository(String username, String repoName) {
        String url = GITHUB_API + "repos/" + username + "/" + repoName + "/branches";

        try {
            ResponseEntity<Branch[]> response = restTemplate.getForEntity(url, Branch[].class);

            if (response.getBody() == null) {
                return List.of();
            }

            return List.of(response.getBody()).stream()
                    .map(branch -> new BranchDto(branch.getName(), branch.getCommit().getSha()))
                    .collect(Collectors.toList());
        } catch (HttpClientErrorException.NotFound e) {
            return List.of();
        }
    }
}
