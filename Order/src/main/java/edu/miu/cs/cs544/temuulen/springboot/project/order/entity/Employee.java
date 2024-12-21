package edu.miu.cs.cs544.temuulen.springboot.project.order.entity;

import jakarta.persistence.Entity;

@Entity
public class Employee extends User {

    private String employeeNumber;
    private String department;

    public Employee() {}

    public Employee(String username, String password, String email, String employeeNumber, String department) {
        super(username, password, email);
        this.employeeNumber = employeeNumber;
        this.department = department;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
