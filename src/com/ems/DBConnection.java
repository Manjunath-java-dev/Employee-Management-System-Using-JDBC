package com.ems;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static Properties properties = new Properties();
    static {

        try(FileInputStream fis =new FileInputStream("C:\\Users\\manju\\Downloads\\EmployeeManagementSystem\\src\\main\\resources\\db.properties")){
         properties.load(fis);
}catch (IOException e){
    e.printStackTrace();
}
    }
    public static Connection getConnection() throws SQLException{
        Connection connection = DriverManager.getConnection(
                properties.getProperty("db.url"),
                properties.getProperty("db.username"),
                properties.getProperty("db.password")
        );

        return connection;
    }




}
