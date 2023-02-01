package org.example.service;

public class ComponentManager {

    private static final EmployeeService employeeService = new EmployeeService();

    public static EmployeeService getEmployeeService() {
        return employeeService;
    }
}
