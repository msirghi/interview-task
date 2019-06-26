package com.interview.interview.parse;

import com.interview.interview.configs.DatabaseConfig;
import com.interview.interview.model.TaskModel;
import com.opencsv.CSVReader;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

@ToString
public class ParseFile {
  private CSVReader reader;
  private long recordsReceived;
  private long recordsFailed;
  private DatabaseConfig databaseConfig;
  private static final Logger LOGGER = LoggerFactory.getLogger(ParseFile.class);

  public ParseFile(String path) {
    try {
      reader = new CSVReader(new FileReader(path), ',');
      reader.readNext();
      databaseConfig = new DatabaseConfig();
    } catch (IOException e) {
      LOGGER.error("File not found.");
    }
  }

  public void parseCvs() throws Exception {
    String[] row;
    String[] tempRow;
    FileWriter csvWriter = new FileWriter("bad-data-"+ System.currentTimeMillis() + ".csv");

    while ((row = reader.readNext()) != null) {
      recordsReceived++;
      int goodRecordCounter = 1;
      tempRow = row;
      TaskModel record = new TaskModel();
      try {
        for (String column : row) {
          emptyCheck(column);
          setModelField(record, goodRecordCounter++, column);
        }
        databaseConfig.performSqlStatement(record);
      } catch (IllegalAccessException e) {
        recordsFailed++;
        int badRecordColumn = 1;
        TaskModel badRecord = new TaskModel();
        for (String token : tempRow) {
          setModelField(badRecord, badRecordColumn++, token);
        }
        csvWriter.append(String.join(",", badRecord.toString()));
        csvWriter.append("\n");
        csvWriter.flush();
      }
    }
    LOGGER.error("Records received: " + recordsReceived);
    LOGGER.error("Records successful: " + (recordsReceived - recordsFailed));
    LOGGER.error("Records failed: " + recordsFailed);

    csvWriter.close();
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
