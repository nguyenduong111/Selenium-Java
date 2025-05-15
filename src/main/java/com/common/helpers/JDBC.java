package com.common.helpers;

import com.common.ultilities.LogUtils;
import com.common.ultilities.PropertiesFile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBC {
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public JDBC(String dataBaseName) {
        PropertiesFile.setPropertiesFile();
        try {
            connection = DriverManager.getConnection(
                    PropertiesFile.getPropValue("urlDB") + dataBaseName,
                    PropertiesFile.getPropValue("userDB"),
                    PropertiesFile.getPropValue("passwordDB")
            );
            LogUtils.info("Connected to DB successfully.");
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("select * from time_sheet_data");
//
//            while (resultSet.next()) {
//                System.out.println(resultSet.getString("employee") + " - " + resultSet.getString("date") + " - " + resultSet.getString("hours") + " - " + resultSet.getString("remark"));
//            }

        }catch (Exception e) {
            LogUtils.error("Connection to DB failed. " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if(resultSet != null) {
                resultSet.close();
            }
            if(statement != null) {
                statement.close();
            }
            if(connection != null) {
                connection.close();
            }
            LogUtils.info("Connection closed.");
        } catch (Exception e) {
            LogUtils.error("Cannot close connection. " + e.getMessage());
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String query) {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            LogUtils.info("Query executed successfully.");
            return resultSet;
        } catch (Exception e) {
            LogUtils.error("Query execution failed. " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }


}
