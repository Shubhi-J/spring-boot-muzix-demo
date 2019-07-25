package com.stackroute.muzix.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

// entity class to map with a relation
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Track {

    // properties of track
    // id is the identity field
    @Id
    private int id;

    private String name;

    private String comment;
}
