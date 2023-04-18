package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private Year year;

    @JsonIgnore
    @OneToMany
    private List<File> files;

    @ManyToOne
    private SemesterType semesterType;

    @JsonIgnore
    @ManyToMany(mappedBy = "favoriteSubjects", fetch = FetchType.EAGER)
    private List<User> usersSubject;


    public Subject(String name, SemesterType semesterType, Year year, File file) {
        this.name = name;
        this.year = year;
        this.semesterType = semesterType;
        this.files.add(file);
    }
    public Subject(String name, SemesterType semesterType, Year year) {
        this.name = name;
        this.year = year;
        this.semesterType = semesterType;
    }
}
