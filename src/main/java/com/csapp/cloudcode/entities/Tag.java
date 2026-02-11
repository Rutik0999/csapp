package com.csapp.cloudcode.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String tagId;
    private String tagTitle;
    private String TagDescription;

    @ManyToMany(mappedBy = "tags")
    private Set<Issue> issues = new HashSet<>();
}
