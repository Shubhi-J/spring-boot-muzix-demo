package com.stackroute.muzix.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// using annotations to create getter setter and constructors
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Track {

    // properties of track
    // id is the identity field
    private int id;

    private String name;

    private String comment;
}
