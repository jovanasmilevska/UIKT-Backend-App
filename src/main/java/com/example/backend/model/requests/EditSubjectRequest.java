package com.example.backend.model.requests;

import com.example.backend.model.SemesterType;
import com.example.backend.model.Year;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditSubjectRequest {

    private String name;
    private Year year;
    private SemesterType semesterType;
    private Long id;
}
