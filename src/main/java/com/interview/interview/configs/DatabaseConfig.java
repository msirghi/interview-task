package com.interview.interview.configs;

import com.interview.interview.model.TaskModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConfig {
  private static Connection connection;
  private static PreparedStatement prepStatement;
  private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConfig.class);
  private static final String DATABASE_DRIVER = "org.h2.Driver";
  private static final String DATABASE_CONNECTION = "jdbc:h2:mem:testdb";
  private static final String DATABASE_USER = "sa";
  private static final String DATABASE_PASSWORD = "secret";
  private static final String sqlQuery = "INSERT INTO X (A, B, C, D, E, F, G, H, I, J) " +
          "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

  public DatabaseConfig() {
    connectDB();
    getDBConnection();
  }

  private static Connection getDBConnection() {
    Connection H2DBConnection = null;
    try {
      Class.forName(DATABASE_DRIVER);
      H2DBConnection = DriverManager
              .getConnection(DATABASE_CONNECTION, DATABASE_USER, DATABASE_PASSWORD);
      return H2DBConnection;
    } catch (ClassNotFoundException ex) {
      LOGGER.warn(ex.toString());
    } catch (SQLException ex) {
      LOGGER.warn("Error connecting to db. Verify data.");
    }
    return H2DBConnection;
  }

  public static void connectDB() {
    connection = getDBConnection();
    try {
      connection.setAutoCommit(false);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void performSqlStatement(TaskModel taskModel) {
    try {
      prepStatement = connection.prepareStatement(sqlQuery);
      prepStatement.setString(1, taskModel.getAColumn());
      prepStatement.setString(2, taskModel.getBColumn());
      prepStatement.setString(3, taskModel.getCColumn());
      prepStatement.setString(4, taskModel.getDColumn());
      prepStatement.setString(5, taskModel.getEColumn());
      prepStatement.setString(6, taskModel.getFColumn());
      prepStatement.setString(7, taskModel.getGColumn());
      prepStatement.setString(8, taskModel.getHColumn());
      prepStatement.setString(9, taskModel.getIColumn());
      prepStatement.setString(10, taskModel.getJColumn());
      prepStatement.executeUpdate();
      prepStatement.close();
      connection.commit();
    } catch (SQLException e) {
      LOGGER.warn("Error while executing sql statement.");
    }
  }

}
