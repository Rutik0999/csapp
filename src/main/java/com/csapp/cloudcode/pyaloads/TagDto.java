package com.csapp.cloudcode.pyaloads;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TagDto {

    @Nullable
    private String tagId;
    private String tagTitle;
    private String TagDescription;
}
