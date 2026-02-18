package com.csapp.cloudcode.repo;

import com.csapp.cloudcode.entities.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IssueRepo extends JpaRepository<Issue, UUID> {

    Optional<Issue> findByIssueId(UUID issueId);
}
