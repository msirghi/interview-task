package com.interview.interview.parse;

import com.interview.interview.model.TaskModel;
import com.opencsv.CSVReader;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;
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

//  public static void createDBTable() {
//    String CreateSQLQuery = "CREATE TABLE WORKERS(employeeid int auto_increment primary key, "
//            + "                                firstname varchar(100), "
//            + "                                lastname varchar(100), "
//            + "                                department varchar(100),"
//            + "                                location varchar(10))";
//    Connection connection = getDBConnection();
//    try {
//      //Set auto commit to false
//      connection.setAutoCommit(false);
//      //Create a Statement Object
//      Statement statement = connection.createStatement();
//      //Execute the statement
//      statement.execute(CreateSQLQuery);
//      //Close the Statement Object
//      statement.close();
//      //Close the Connection Object
//      connection.commit();
//    }
//    catch(Exception ex) {
//      System.out.println(ex.toString());
//    }
//    System.out.println("Successfully Created WORKERS Table!");
//  }

  private static Connection getDBConnection() {
    Connection H2DBConnection = null;
    try {
      Class.forName(DATABASE_DRIVER);
    }
    catch (ClassNotFoundException ex) {
      System.out.println(ex.toString());
    }
    try {
      H2DBConnection = DriverManager.getConnection(DATABASE_CONNECTION, DATABASE_USER, DATABASE_PASSWORD);
      return H2DBConnection;
    }
    catch (SQLException ex) {
      System.out.println(ex.toString());
    }
    return H2DBConnection;
  }

  public ParseFile(String path) {
    try {
      reader = new CSVReader(new FileReader(path), ',');
    } catch (FileNotFoundException e) {
      logger.error("File not found.");
    }
  }

  public void parseCvs() throws Exception {
    List<TaskModel> result = new ArrayList<>();
    int i = 0;
    String[] nextLine;

    Connection connection = getDBConnection();

    PreparedStatement prepStatement;
    while ((nextLine = reader.readNext()) != null && i++ < 5) {
      connection.setAutoCommit(false);

      int j = 0;
      if (i != 1) {
        TaskModel taskModel = new TaskModel();
        for (String token : nextLine) {
          setModelField(taskModel, j++, token);
        }
        prepStatement = connection
                .prepareStatement("INSERT INTO X (A, B, C, D, E, F, G, H, I, J) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
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
        int rc = prepStatement.executeUpdate();
        prepStatement.close();

        //Close the Connection Object
        connection.commit();
        result.add(taskModel);
      }
    }
    result.forEach(System.out::println);
    reader.close();
  }

  private static void setModelField(TaskModel taskModel, Integer j, String token) {
    switch (j) {
      case 0:
        taskModel.setAColumn(token);
      case 1:
        taskModel.setBColumn(token);
      case 2:
        taskModel.setCColumn(token);
      case 3:
        taskModel.setDColumn(token);
      case 4:
        taskModel.setEColumn(token);
      case 5:
        taskModel.setFColumn(token);
      case 6:
        taskModel.setGColumn(token);
      case 7:
        taskModel.setHColumn(token);
      case 8:
        taskModel.setIColumn(token);
      case 9:
        taskModel.setJColumn(token);
    }
  }
}
