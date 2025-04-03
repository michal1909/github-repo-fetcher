package com.example.github_repo_fetcher.service;

import com.example.github_repo_fetcher.dto.RepositoryDto;
import com.example.github_repo_fetcher.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class GitHubServiceTest {

    @Autowired
    private GitHubService gitHubService;

    @Test
    public void givenValidUser_whenGetRepositories_thenReturnNonForkedRepos() {

        String username = "michal1909";

        List<RepositoryDto> repositories = gitHubService.getNonForkRepositories(username);

        assertThat(repositories).isNotEmpty();
        assertThat(repositories).allMatch(repo -> repo.getBranches() != null);
    }

    @Test
    public void givenUserWithoutRepos_whenGetRepositories_thenReturnEmptyList() {

        String username = "tohare8165";

        List<RepositoryDto> repositories = gitHubService.getNonForkRepositories(username);

        assertThat(repositories).isEmpty();
    }

    @Test
    public void givenInvalidUser_whenGetRepositories_thenThrowUserNotFoundException() {

        String username = "notExistingUser";

        assertThatThrownBy(() -> gitHubService.getNonForkRepositories(username))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage("User not found");
    }


}
