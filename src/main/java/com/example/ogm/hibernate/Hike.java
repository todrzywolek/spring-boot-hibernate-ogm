package com.example.ogm.hibernate;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.*;

@Entity
public class Hike {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Type(type = "uuid-char")
    private UUID id;

    private String description;
    private Date date;
    private double difficulty;

    @ManyToOne
    private Person organizer;

    @ElementCollection
    @OrderColumn(name = "sectionNo")
    private List<HikeSection> sections;

    public Hike() {
    }

    public Hike(String description, Date date, double difficulty, HikeSection... sections) {
        this.description = description;
        this.date = date;
        this.difficulty = difficulty;
        this.sections = sections != null ? new ArrayList<>(Arrays.asList(sections)) : new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
    }

    public Person getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Person organizer) {
        this.organizer = organizer;
    }

    public List<HikeSection> getSections() {
        return sections;
    }

    public void setSections(List<HikeSection> sections) {
        this.sections = sections;
    }
}
