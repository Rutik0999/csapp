package com.csapp.cloudcode.pyaloads;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    @Nullable
    private String customerId;

    private String customerName;

    private String customerEmail;
    private String password;
    private String address;
    private String about;

    private List<IssueDto> listOfIssueDto;
}