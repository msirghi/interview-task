package com.interview.interview.parse;

import com.interview.interview.model.TaskModel;
import com.opencsv.CSVReader;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

@ToString
public class ParseFile {
  private CSVReader reader;
  private long recordsReceived;
  private long recordsFailed;
  private Connection connection;
  private PreparedStatement prepStatement;

  private static final Logger LOGGER = LoggerFactory.getLogger(ParseFile.class);
  private static final String DATABASE_DRIVER = "org.h2.Driver";
  private static final String DATABASE_CONNECTION = "jdbc:h2:mem:testdb";
  private static final String DATABASE_USER = "sa";
  private static final String DATABASE_PASSWORD = "secret";
  private static final String sqlQuery = "INSERT INTO X (A, B, C, D, E, F, G, H, I, J) " +
          "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

  public ParseFile(String path) {
    try {
      reader = new CSVReader(new FileReader(path), ',');
      reader.readNext();
    } catch (IOException e) {
      LOGGER.error("File not found.");
    }
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

  public void parseCvs() throws Exception {
    String[] nextLine;
    String[] tempLine;
    FileWriter csvWriter = new FileWriter("bad-data-"+ System.currentTimeMillis() + ".csv");
    connection = getDBConnection();
    connection.setAutoCommit(false);

    while ((nextLine = reader.readNext()) != null) {
      recordsReceived++;
      int j = 1;
      tempLine = nextLine;
      TaskModel taskModel = new TaskModel();
      try {
        for (String token : nextLine) {
          emptyCheck(token);
          setModelField(taskModel, j++, token);
        }
        performSqlStatement(taskModel);
      } catch (IllegalAccessException e) {
        recordsFailed++;
        int q = 1;
        TaskModel taskModel1 = new TaskModel();
        for (String token : tempLine) {
          setModelField(taskModel1, q++, token);
        }
        csvWriter.append(String.join(",", taskModel1.toString()));
        csvWriter.append("\n");
        csvWriter.flush();
      }
    }
    LOGGER.info("Records received: " + recordsReceived);
    LOGGER.info("Records successful: " + (recordsReceived - recordsFailed));
    LOGGER.info("Records failed: " + recordsFailed);

    csvWriter.close();
    reader.close();
  }

  private void performSqlStatement(TaskModel taskModel) {
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

  private static void setModelField(TaskModel taskModel, Integer j, String token) {
    switch (j) {
      case 1:
        taskModel.setAColumn(token);
      case 2:
        taskModel.setBColumn(token);
      case 3:
        taskModel.setCColumn(token);
      case 4:
        taskModel.setDColumn(token);
      case 5:
        taskModel.setEColumn(token);
      case 6:
        taskModel.setFColumn(token);
      case 7:
        taskModel.setGColumn(token);
      case 8:
        taskModel.setHColumn(token);
      case 9:
        taskModel.setIColumn(token);
      case 10:
        taskModel.setJColumn(token);
    }
  }

  private static void emptyCheck(String token) throws IllegalAccessException {
    if (token.isEmpty())
      throw new IllegalAccessException();
  }
}
