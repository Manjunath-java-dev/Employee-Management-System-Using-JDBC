package com.ems;

public class Employee {

   private String employee_name;
   private String email;
   private double salary;
   private int department_id;

    public Employee(
                    String employee_name,
                    String email,
                    double salary,
                    int department_id) {

        this.employee_name = employee_name;
        this.email = email;
        this.salary = salary;
        this.department_id = department_id;
    }

    public Employee() {
    }





    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }


}
