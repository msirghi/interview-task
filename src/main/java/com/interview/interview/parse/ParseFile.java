package com.interview.interview.parse;

import com.interview.interview.model.TaskModel;
import com.opencsv.CSVReader;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ToString
public class ParseFile {
  private CSVReader reader;
  private Logger logger = LoggerFactory.getLogger(ParseFile.class);

  private static final String DATABASE_DRIVER = "org.h2.Driver";
  private static final String DATABASE_CONNECTION = "jdbc:h2:mem:testdb";
  private static final String DATABASE_USER = "sa";
  private static final String DATABASE_PASSWORD = "secret";
  private static final String sqlQuery = "INSERT INTO X (A, B, C, D, E, F, G, H, I, J) " +
          "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

  private static Connection getDBConnection() {
    Connection H2DBConnection = null;
    try {
      Class.forName(DATABASE_DRIVER);
    } catch (ClassNotFoundException ex) {
      System.out.println(ex.toString());
    }
    try {
      H2DBConnection = DriverManager.getConnection(DATABASE_CONNECTION, DATABASE_USER, DATABASE_PASSWORD);
      return H2DBConnection;
    } catch (SQLException ex) {
      System.out.println(ex.toString());
    }
    return H2DBConnection;
  }

  public ParseFile(String path) {
    try {
      reader = new CSVReader(new FileReader(path), ',');
      reader.readNext();
    } catch (IOException e) {
      logger.error("File not found.");
    }
  }

  public void parseCvs() throws Exception {
    List<TaskModel> result = new ArrayList<>();
    String[] nextLine;
    Connection connection = getDBConnection();
    PreparedStatement prepStatement;
    connection.setAutoCommit(false);

    while ((nextLine = reader.readNext()) != null) {
      int j = 1;
      TaskModel taskModel = new TaskModel();
      try {
        for (String token : nextLine) {
          emptyCheck(token);
          setModelField(taskModel, j++, token);
        }
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
        result.add(taskModel);
      } catch (IllegalAccessException e) {
        continue;
      }
    }
    result.forEach(System.out::println);
    reader.close();
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
