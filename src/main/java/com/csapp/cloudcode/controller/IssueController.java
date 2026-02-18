package com.csapp.cloudcode.controller;

import com.csapp.cloudcode.pyaloads.GlobalApiResponse;
import com.csapp.cloudcode.pyaloads.IssueDto;
import com.csapp.cloudcode.services.impl.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("issue/api")
@CrossOrigin("*")
public class IssueController {

    @Autowired
    public IssueService issueService;

    // ================= CREATE ISSUE =================
    @PostMapping("create")
    public ResponseEntity<GlobalApiResponse> createIssue(@RequestBody IssueDto issueDto) {

        IssueDto createdIssue = issueService.createIssue(issueDto);

        if (createdIssue == null) {
            GlobalApiResponse response = new GlobalApiResponse(
                    false,
                    "Cannot create issue",
                    null,
                    HttpStatus.BAD_REQUEST.toString()
            );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        GlobalApiResponse response = new GlobalApiResponse(
                true,
                "Issue created successfully",
                createdIssue,
                HttpStatus.CREATED.toString()
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // ================= UPDATE ISSUE =================
    @PostMapping("update/issue/{issueId}")
    public ResponseEntity<GlobalApiResponse> updateIssue(
            @PathVariable String issueId,
            @RequestBody IssueDto issueDto
    ) {

        IssueDto updatedIssue = issueService.updateIssue(issueId, issueDto);

        if (updatedIssue == null) {
            GlobalApiResponse response = new GlobalApiResponse(
                    false,
                    "Cannot update issue",
                    null,
                    HttpStatus.BAD_REQUEST.toString()
            );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        GlobalApiResponse response = new GlobalApiResponse(
                true,
                "Issue updated successfully",
                updatedIssue,
                HttpStatus.OK.toString()
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // ================= GET ISSUE BY ID =================
    @GetMapping("getIssue/{issueId}")
    public ResponseEntity<GlobalApiResponse> getIssueById(@PathVariable String issueId) {

        IssueDto issue = issueService.getIssueById(issueId);

        GlobalApiResponse response = new GlobalApiResponse(
                true,
                "Issue fetched successfully",
                issue,
                HttpStatus.OK.toString()
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // ================= GET ALL ISSUES =================
    @GetMapping("getAllIssues")
    public ResponseEntity<GlobalApiResponse> getAllIssues() {

        List<IssueDto> issues = issueService.getAllIssues();

        GlobalApiResponse response = new GlobalApiResponse(
                true,
                "Issue list fetched successfully",
                issues,
                HttpStatus.OK.toString()
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // ================= DELETE ISSUE =================
    @DeleteMapping("delete/issue/{issueId}")
    public ResponseEntity<GlobalApiResponse> deleteIssue(@PathVariable String issueId) {

        issueService.deleteIssue(issueId);

        GlobalApiResponse response = new GlobalApiResponse(
                true,
                "Issue deleted successfully",
                null,
                HttpStatus.OK.toString()
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
