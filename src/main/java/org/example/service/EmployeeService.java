package org.example.service;

import org.example.repository.entities.Pair;
import org.example.repository.entities.Project;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class EmployeeService {

    public HashMap<Long, Pair> findPair(List<Project> data) {
        HashMap<Long, List<Project>> projects = getFiltered(data);
        HashMap<Long, Pair> pairs = new HashMap<>();

        for (Map.Entry<Long, List<Project>> projects1 : projects.entrySet()) {
            Long key = projects1.getKey();
            Period longestPeriod = null;

            for (Project employee : projects1.getValue()) {

                for (Project employee1 : projects1.getValue()) {
                    if (!Objects.equals(employee1.getEmpID(), employee.getEmpID())) {
                        if (isOverlapping(employee, employee1)) {
                            Period period = getOverlapPeriod(employee, employee1);

                            if (longestPeriod == null || period.getDays() > longestPeriod.getDays()) {
                                longestPeriod = period;
                                pairs.remove(key);
                                pairs.put(key, Pair.builder()
                                        .empId(employee.getEmpID())
                                        .empId2(employee1.getEmpID())
                                        .timeWorkedAsPair(longestPeriod.toTotalMonths())
                                        .build());
                            }

                        }
                    }
                }
            }


        }

        return pairs;

    }

    private Period getOverlapPeriod(Project p, Project project) {
        LocalDate from;
        LocalDate to;
        if (p.getDateFrom().isAfter(project.getDateFrom())) {
            from = p.getDateFrom();
        } else {
            from = project.getDateFrom();
        }
        if (p.getDateTo().isBefore(project.getDateTo())) {
            to = p.getDateTo();
        } else {
            to = project.getDateTo();
        }
        return Period.between(from, to);
    }

    private boolean isOverlapping(Project project1, Project project2) {
        return project1.getDateFrom().isBefore(project2.getDateTo()) && project2.getDateFrom().isBefore(project1.getDateTo());
    }

    private HashMap<Long, List<Project>> getFiltered(List<Project> data) {
        HashMap<Long, List<Project>> filtered = new HashMap<>();
        for (Project pr : data) {

            if (filtered.containsKey(pr.getProjectID())) {
                filtered.get(pr.getProjectID()).add(pr);
            } else {
                List<Project> projectList = new ArrayList<>();
                projectList.add(pr);
                filtered.put(pr.getProjectID(), projectList);
            }
        }
        return filtered;
    }
}
