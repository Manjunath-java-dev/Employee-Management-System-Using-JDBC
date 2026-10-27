
package com.ems;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        EmployeeDAO employeeDAO = new EmployeeDAO();
        DepartmentDAO departmentDAO = new DepartmentDAO();

        while (true) {

            System.out.println("\n===== EMPLOYEE MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Department");
            System.out.println("2. Add Employee");
            System.out.println("3. View All Employees");
            System.out.println("4. Search Employee by ID");
            System.out.println("5. Update Employee Details");
            System.out.println("6. Delete Employee");
            System.out.println("7. Display Employees Department-wise");
            System.out.println("8. Exit");

            System.out.print("\nEnter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {

                    case 1: {
                        System.out.println("Enter department name:");
                        String departmentName = scanner.nextLine();

                        int departmentId =
                                departmentDAO.getOrCreateDepartmentId(departmentName);

                        System.out.println(
                                "Department is ready. Department ID: " + departmentId
                        );
                        break;
                    }

                    case 2: {
                        System.out.println("Enter employee name:");
                        String name = scanner.nextLine();

                        System.out.println("Enter email:");
                        String email = scanner.nextLine();

                        System.out.println("Enter salary:");
                        double salary = scanner.nextDouble();
                        scanner.nextLine();

                        System.out.println("Enter department name:");
                        String departmentName = scanner.nextLine();

                        int departmentId =
                                departmentDAO.getOrCreateDepartmentId(departmentName);

                        Employee employee = new Employee(
                                name, email, salary, departmentId
                        );

                        employeeDAO.addEmployee(employee);
                        break;
                    }

                    case 3:
                        employeeDAO.viewAllEmployees();
                        break;
                    case 4:
                        System.out.print("Enter employee ID to search: ");
                        int employeeId = scanner.nextInt();

                        employeeDAO.searchEmployeeById(employeeId);
                        break;

                    case 5: {
                        System.out.print("Enter employee ID to update: ");
                        int employee_Id = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Enter new employee name: ");
                        String name = scanner.nextLine();

                        System.out.print("Enter new email: ");
                        String email = scanner.nextLine();

                        System.out.print("Enter new salary: ");
                        double salary = scanner.nextDouble();

                        System.out.print("Enter new department ID: ");
                        int departmentId = scanner.nextInt();

                        employeeDAO.updateEmployee(
                                employee_Id, name, email, salary, departmentId
                        );
                        break;
                    }
                    case 6:
                        System.out.print("Enter employee ID to delete: ");
                        int empId = scanner.nextInt();

                        employeeDAO.deleteEmployee(empId);
                        break;

                    case 7:
                        employeeDAO.displayEmployeesDepartmentWise();
                        break;

                    case 8:
                        System.out.println(
                                "Exiting Employee Management System..."
                        );
                        scanner.close();
                        return;

                    default:
                        System.out.println(
                                "Invalid choice! Please enter 1 to 8."
                        );
                }

            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());

            } catch (java.util.InputMismatchException e) {
                System.out.println(
                        "Invalid input. Please enter the correct data type."
                );
                scanner.nextLine();
            }
        }
    }
}
