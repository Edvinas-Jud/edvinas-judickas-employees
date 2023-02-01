package org.example.repository.entities;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class Pair {
    private Long empId;
    private Long empId2;

    private Long timeWorkedAsPair;
}