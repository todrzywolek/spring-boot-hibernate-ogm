package com.example.ogm.bael;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Editor {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String editorId;

    private String editorName;

    @OneToMany(mappedBy = "editor", cascade = CascadeType.PERSIST)
    private Set<Author> assignedAuthors = new HashSet<>();

    // constructors, getters and setters...

    public Editor() {
    }

    public Editor(String editorName) {
        this.editorName = editorName;
    }

    public String getEditorId() {
        return editorId;
    }

    public void setEditorId(String editorId) {
        this.editorId = editorId;
    }

    public String getEditorName() {
        return editorName;
    }

    public void setEditorName(String editorName) {
        this.editorName = editorName;
    }

    public Set<Author> getAssignedAuthors() {
        return assignedAuthors;
    }

    public void setAssignedAuthors(Set<Author> assignedAuthors) {
        this.assignedAuthors = assignedAuthors;
    }
}