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
public class FileParser {
  private CSVReader reader;
  private long recordsReceived;
  private long recordsFailed;
  private DatabaseConfig databaseConfig;
  private static final Logger LOGGER = LoggerFactory.getLogger(FileParser.class);

  public FileParser(String path) {
    try {
      reader = new CSVReader(new FileReader(path), ',');
      reader.readNext();
      databaseConfig = new DatabaseConfig();
    } catch (IOException e) {
      LOGGER.error("File not found.");
    }
  }

  public synchronized void parseCvs() throws Exception {
    String[] row;
    String[] tempRow;
    FileWriter csvWriter = new FileWriter("bad-data-"
            + System.currentTimeMillis() + ".csv");

    while ((row = reader.readNext()) != null) {
      recordsReceived++;
      int columnCounter = 1;
      tempRow = row;
      RecordModel record = new RecordModel();
      try {
        for (String columnText : row) {
          emptyCheck(columnText);
          record.setColumn(columnCounter++, columnText);
        }
        databaseConfig.performSqlStatement(record);
      } catch (IllegalAccessException e) {
        recordsFailed++;
        csvWriter.append(String.join(",", tempRow))
                .append("\n")
                .flush();
      }
    }
    csvWriter.close();
    reader.close();
    printStatistics();
  }

  private static void emptyCheck(String token) throws IllegalAccessException {
    if (token.isEmpty()) {
      throw new IllegalAccessException();
    }
  }

  private void printStatistics() {
    LOGGER.error("Records received: " + recordsReceived);
    LOGGER.error("Records successful: " + (recordsReceived - recordsFailed));
    LOGGER.error("Records failed: " + recordsFailed);
  }
}
