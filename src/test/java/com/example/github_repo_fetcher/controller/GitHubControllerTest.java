package com.example.github_repo_fetcher.controller;

import com.example.github_repo_fetcher.dto.RepositoryDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GitHubControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void givenValidUser_whenGetRepositories_thenReturn200() {

        String username = "michal1909";

        ResponseEntity<RepositoryDto[]> response = restTemplate.getForEntity("/repositories/" + username, RepositoryDto[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotEmpty();
    }

    @Test
    public void givenInvalidUser_whenGetRepositories_thenReturn404() {

        String username = "notExistingUser";

        ResponseEntity<String> response = restTemplate.getForEntity("/repositories/" + username, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).contains("\"status\":404");
    }

}
