package com.interview.interview.parse;

import com.interview.interview.config.DatabaseConfig;
import com.interview.interview.model.RecordModel;
import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    FileWriter csvWriter = new FileWriter("bad-data-"
            + System.currentTimeMillis() + ".csv");

    while ((row = reader.readNext()) != null) {
      recordsReceived++;
      int goodRecordCounter = 1;
      tempRow = row;
      RecordModel record = new RecordModel();
      try {
        for (String column : row) {
          emptyCheck(column);
          setModelField(record, goodRecordCounter++, column);
        }
        databaseConfig.performSqlStatement(record);
      } catch (IllegalAccessException e) {
        recordsFailed++;
        int badRecordColumn = 1;
        RecordModel badRecord = new RecordModel();
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

  private static void setModelField(RecordModel recordModel, Integer j, String token) {
    switch (j) {
      case 1:
        recordModel.setAColumn(token);
        break;
      case 2:
        recordModel.setBColumn(token);
        break;
      case 3:
        recordModel.setCColumn(token);
        break;
      case 4:
        recordModel.setDColumn(token);
        break;
      case 5:
        recordModel.setEColumn(token);
        break;
      case 6:
        recordModel.setFColumn(token);
        break;
      case 7:
        recordModel.setGColumn(token);
        break;
      case 8:
        recordModel.setHColumn(token);
        break;
      case 9:
        recordModel.setIColumn(token);
        break;
      case 10:
        recordModel.setJColumn(token);
        break;
      default:
        break;
    }
  }

  private static void emptyCheck(String token) throws IllegalAccessException {
    if (token.isEmpty()) {
      throw new IllegalAccessException();
    }
  }
}
