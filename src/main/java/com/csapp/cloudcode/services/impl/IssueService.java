package com.csapp.cloudcode.services.impl;

import com.csapp.cloudcode.pyaloads.IssueDto;

import java.util.List;

public interface IssueService {

    IssueDto createIssue(IssueDto issueDto);

    IssueDto updateIssue(String issueId, IssueDto issueDto);

    IssueDto getIssueById(String issueId);

    List<IssueDto> getAllIssues();

    void deleteIssue(String issueId);
}
