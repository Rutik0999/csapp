package com.csapp.cloudcode.pyaloads;


import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IssueDto {

    @Nullable
    private String issueId;

    private String issueTitle;

    private String issueDescription;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String status;

    private String customerId;
}

