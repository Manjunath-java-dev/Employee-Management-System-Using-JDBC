package com.ems;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAO {


    public void addEmployee(Employee employee) throws SQLException {

        String sql = "INSERT INTO employees " +
                "(employee_name, email, salary, department_id) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql)) {

            preparedStatement.setString(1, employee.getEmployee_name());
            preparedStatement.setString(2, employee.getEmail());
            preparedStatement.setDouble(3, employee.getSalary());
            preparedStatement.setInt(4, employee.getDepartment_id());

            preparedStatement.executeUpdate();

            System.out.println("Employee added successfully!");
        }

    }

    public void viewAllEmployees() throws SQLException {

        String sql = "SELECT * FROM employees";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {

                System.out.println("Employee ID: "
                        + resultSet.getInt("employee_id"));

                System.out.println("Name: "
                        + resultSet.getString("employee_name"));

                System.out.println("Email: "
                        + resultSet.getString("email"));

                System.out.println("Salary: "
                        + resultSet.getDouble("salary"));

                System.out.println("Department ID: "
                        + resultSet.getInt("department_id"));

                System.out.println("--------------------------");
            }
        }
    }

    public void searchEmployeeById(int employeeId) throws SQLException {

        String sql = "SELECT * FROM employees WHERE employee_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, employeeId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {

                    System.out.println("Employee ID: "
                            + resultSet.getInt("employee_id"));

                    System.out.println("Name: "
                            + resultSet.getString("employee_name"));

                    System.out.println("Email: "
                            + resultSet.getString("email"));

                    System.out.println("Salary: "
                            + resultSet.getDouble("salary"));

                    System.out.println("Department ID: "
                            + resultSet.getInt("department_id"));

                } else {
                    System.out.println("Employee not found!");
                }
            }
        }
    }

    public void updateEmployee(int employeeId, String name,
                               String email, double salary,
                               int departmentId) throws SQLException {

        String sql = "UPDATE employees SET employee_name = ?, " +
                "email = ?, salary = ?, department_id = ? " +
                "WHERE employee_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setDouble(3, salary);
            preparedStatement.setInt(4, departmentId);
            preparedStatement.setInt(5, employeeId);

            int rows = preparedStatement.executeUpdate();

            if (rows > 0) {
                System.out.println("Employee updated successfully!");
            } else {
                System.out.println("Employee not found!");
            }
        }
    }

    public void deleteEmployee(int employeeId) throws SQLException {

        String sql = "DELETE FROM employees WHERE employee_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, employeeId);

            int rows = preparedStatement.executeUpdate();

            if (rows > 0) {
                System.out.println("Employee deleted successfully!");
            } else {
                System.out.println("Employee not found!");
            }
        }
    }

    public void displayEmployeesDepartmentWise() throws SQLException {

        String sql = "SELECT e.employee_id, e.employee_name, " +
                "e.email, e.salary, d.department_name " +
                "FROM employees e " +
                "INNER JOIN department d " +
                "ON e.department_id = d.department_id " +
                "ORDER BY d.department_name";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            boolean found = false;

            while (resultSet.next()) {
                found = true;

                System.out.println("Employee ID: "
                        + resultSet.getInt("employee_id"));

                System.out.println("Name: "
                        + resultSet.getString("employee_name"));

                System.out.println("Email: "
                        + resultSet.getString("email"));

                System.out.println("Salary: "
                        + resultSet.getDouble("salary"));

                System.out.println("Department: "
                        + resultSet.getString("department_name"));

                System.out.println("--------------------------------");
            }

            if (!found) {
                System.out.println("No employees found!");
            }
        }
    }
}
