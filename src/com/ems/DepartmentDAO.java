
package com.ems;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DepartmentDAO {

    public int getOrCreateDepartmentId(String departmentName)
            throws SQLException {

        String selectSql =
                "SELECT department_id FROM department WHERE department_name = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement selectStatement =
                     connection.prepareStatement(selectSql)) {

            selectStatement.setString(1, departmentName);

            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("department_id");
                }
            }

            String insertSql =
                    "INSERT INTO department (department_name) VALUES (?)";

            try (PreparedStatement insertStatement =
                         connection.prepareStatement(
                                 insertSql, Statement.RETURN_GENERATED_KEYS)) {

                insertStatement.setString(1, departmentName);
                insertStatement.executeUpdate();

                try (ResultSet generatedKeys =
                             insertStatement.getGeneratedKeys()) {

                    if (generatedKeys.next()) {
                        System.out.println("Department added successfully!");
                        return generatedKeys.getInt(1);
                    }
                }
            }
        }

        throw new SQLException("Could not get or create department ID");
    }
}
