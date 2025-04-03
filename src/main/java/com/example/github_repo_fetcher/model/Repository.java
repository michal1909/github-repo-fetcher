package com.example.github_repo_fetcher.model;

import java.util.List;

public class Repository {

    private String name;
    private Owner owner;
    private boolean fork;

    public String getName() {
        return name;
    }

    public Owner getOwner() {
        return owner;
    }

    public boolean isFork() {
        return fork;
    }

}
