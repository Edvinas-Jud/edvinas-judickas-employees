package org.example.service;

import org.example.repository.entities.Pair;
import org.example.repository.entities.Project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Reader {
    private final String filePath = "C:\\Users\\Edwin\\IdeaProjects\\employees\\src\\main\\resources\\mockData\\projectData.csv";

    public void read() {
        List<Project> projects = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine();
            String line = reader.readLine();
            while (line != null) {
                String[] properties = line.split(",");
                if (properties.length != 4) {
                    System.err.println("Skipped line!");
                    line = reader.readLine();
                    continue;
                }
                for (int i = 0; i < properties.length; i++) {
                    properties[i] = properties[i].trim();
                }
                projects.add(Project.builder()
                        .EmpID(Long.valueOf(properties[0]))
                        .ProjectID(Long.valueOf(properties[1]))
                        .DateFrom(Util.convertDate(properties[2]))
                        .DateTo(Util.convertDate(properties[3]))
                        .build());

                line = reader.readLine();
            }
            HashMap<Long, Pair> pairs = ComponentManager.getEmployeeService().findPair(projects);
            System.out.println(pairs.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
