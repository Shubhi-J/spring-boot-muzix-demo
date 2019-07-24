package com.stackroute.muzix.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

// entity class to map with a relation
@Entity
public class Track {

    // properties of track
    // id is the identity field
    @Id
    private int id;

    private String name;

    private String comment;

    // no parameter constructor
    public Track() {
    }

    // parameterised constructor
    public Track(int id, String name, String comment) {
        this.id = id;
        this.name = name;
        this.comment = comment;
       }

    // getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setFirstName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    // toString method
    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
