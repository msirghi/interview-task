package com.interview.interview.config;

import com.interview.interview.model.RecordModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseConfig {
  private static Connection connection;
  private static PreparedStatement prepStatement;
  private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConfig.class);
  private static final String DATABASE_DRIVER = "org.h2.Driver";
  private static final String DATABASE_CONNECTION = "jdbc:h2:mem:testdb";
  private static final String DATABASE_USER = "sa";
  private static final String DATABASE_PASSWORD = "secret";
  private static final String sqlQuery = "INSERT INTO X (A, B, C, D, E, F, G, H, I, J) "
        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

  public DatabaseConfig() {
    connectDB();
    getDbConnection();
  }

  private static Connection getDbConnection() {
    Connection h2DbConnection = null;
    try {
      Class.forName(DATABASE_DRIVER);
      h2DbConnection = DriverManager
              .getConnection(DATABASE_CONNECTION, DATABASE_USER, DATABASE_PASSWORD);
      return h2DbConnection;
    } catch (ClassNotFoundException ex) {
      LOGGER.warn(ex.toString());
    } catch (SQLException ex) {
      LOGGER.warn("Error connecting to db. Verify data.");
    }
    return h2DbConnection;
  }

  private static void connectDB() {
    connection = getDbConnection();
    try {
      connection.setAutoCommit(false);
    } catch (SQLException e) {
      LOGGER.error("Error while connecting to database.");
    }
  }

  public void performSqlStatement(RecordModel recordModel) {
    try {
      prepStatement = connection.prepareStatement(sqlQuery);
      for (int i = 1; i <= 10; i++) {
        prepStatement.setString(i, recordModel.getColumn(i));
      }
      prepStatement.executeUpdate();
      prepStatement.close();
      connection.commit();
    } catch (SQLException e) {
      LOGGER.warn("Error while executing sql statement.");
    }
  }

}
