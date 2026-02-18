package com.csapp.cloudcode.services.impl;

import com.csapp.cloudcode.entities.Customer;
import com.csapp.cloudcode.entities.Issue;
import com.csapp.cloudcode.entities.Tag;
import com.csapp.cloudcode.exception.ResourceNotFoundException;
import com.csapp.cloudcode.pyaloads.IssueDto;
import com.csapp.cloudcode.pyaloads.TagDto;
import com.csapp.cloudcode.repo.CustomerRepo;
import com.csapp.cloudcode.repo.IssueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class IssueServiceImpl implements com.csapp.cloudcode.services.impl.IssueService {

    @Autowired
    private IssueRepo issueRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public IssueDto createIssue(IssueDto issueDto) {

        Issue issue = new Issue();

        issue.setIssueTitle(issueDto.getIssueTitle());
        issue.setIssueDescription(issueDto.getIssueDescription());

        Customer customer = customerRepo.findById(issueDto.getCustomerId())   // ✅ CHANGED
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Customer",
                                "CustomerId",
                                issueDto.getCustomerId()
                        ));

        issue.setCustomer(customer);

        issue.setCreatedAt(LocalDateTime.now());
        issue.setUpdatedAt(LocalDateTime.now());
        issue.setStatus("OPEN");

        if (issueDto.getTags() != null) {
            issue.setTags(
                    issueDto.getTags()
                            .stream()
                            .map(this::dtoToTag)
                            .collect(Collectors.toSet())
            );
        }

        Issue savedIssue = issueRepo.save(issue);
        return issueToDto(savedIssue);
    }

    @Override
    public IssueDto getIssueById(String issueId) {

        Issue issue = issueRepo.findById(UUID.fromString(String.valueOf(UUID.fromString(issueId))))
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Issue",
                                "IssueId",
                                issueId
                        ));

        return issueToDto(issue);
    }

    @Override
    public IssueDto updateIssue(String issueId, IssueDto issueDto) {

        Issue issue = issueRepo.findById(UUID.fromString(String.valueOf(UUID.fromString(issueId))))
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Issue",
                                "IssueId",
                                issueId
                        ));

        issue.setIssueTitle(issueDto.getIssueTitle());
        issue.setIssueDescription(issueDto.getIssueDescription());
        issue.setStatus(issueDto.getStatus());
        issue.setUpdatedAt(LocalDateTime.now());

        if (issueDto.getTags() != null) {
            issue.setTags(
                    issueDto.getTags()
                            .stream()
                            .map(this::dtoToTag)
                            .collect(Collectors.toSet())
            );
        }

        Issue updatedIssue = issueRepo.save(issue);
        return issueToDto(updatedIssue);
    }

    @Override
    public List<IssueDto> getAllIssues() {

        List<Issue> issues = issueRepo.findAll();

        return issues.stream()
                .map(this::issueToDto)
                .toList();
    }

    public void deleteIssue(String issueId) {

        Issue issue = issueRepo.findById(UUID.fromString(String.valueOf(UUID.fromString(issueId))))
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Issue",
                                "IssueId",
                                issueId
                        ));

        issueRepo.delete(issue);
    }

    public Issue dtoToIssue(IssueDto issueDto){
        Issue issue = new Issue();
        issue.setIssueId(UUID.fromString(issueDto.getIssueId()));
        issue.setIssueDescription(issueDto.getIssueDescription());
        issue.setIssueTitle(issueDto.getIssueTitle());
        issue.setCreatedAt(issueDto.getCreatedAt());
        issue.setUpdatedAt(LocalDateTime.now());
        issue.setStatus(issueDto.getStatus());
        if (issueDto.getTags()!=null){
            Set<Tag> tags = issueDto.getTags()
                    .stream()
                    .map(this::dtoToTag)
                    .collect(Collectors.toSet());

            issue.setTags(tags);
        }

        return issue;
    }

    public IssueDto issueToDto (Issue issue){
        IssueDto issueDto = new IssueDto();
        issueDto.setIssueId(String.valueOf(issue.getIssueId()));
        issueDto.setIssueDescription(issue.getIssueDescription());
        issueDto.setIssueTitle(issue.getIssueTitle());
        issueDto.setCreatedAt(issue.getCreatedAt());
        issueDto.setUpdatedAt(issue.getUpdatedAt());
        issueDto.setStatus(issue.getStatus());
        issueDto.setCustomerId(issue.getCustomer().getCustomerId());
        if (issue.getTags()!=null){
            Set<TagDto> tagDtos = issue.getTags()
                    .stream()
                    .map(this::tagToDto)
                    .collect(Collectors.toSet());

            issueDto.setTags(tagDtos);
        }


        return issueDto;
    }

    private Tag dtoToTag(TagDto dto) {
        Tag tag = new Tag();
        tag.setTagId(dto.getTagId());
        tag.setTagTitle(dto.getTagTitle());
        tag.setTagDescription(dto.getTagDescription());
        return tag;
    }

    private TagDto tagToDto(Tag tag) {
        TagDto dto = new TagDto();
        dto.setTagId(tag.getTagId());
        dto.setTagTitle(tag.getTagTitle());
        dto.setTagDescription(tag.getTagDescription());
        return dto;
    }
}
