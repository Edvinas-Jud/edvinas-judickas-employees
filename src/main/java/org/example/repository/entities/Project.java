package org.example.repository.entities;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Project {
    private Long EmpID;
    private Long ProjectID;
    private LocalDate DateFrom;
    private LocalDate DateTo;

}
