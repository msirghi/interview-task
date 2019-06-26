package com.interview.interview.parse;

import com.interview.interview.model.TaskModel;
import com.opencsv.CSVReader;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@ToString
public class ParseFile {
  private CSVReader reader;
  private Logger logger = LoggerFactory.getLogger(ParseFile.class);

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

    while ((nextLine = reader.readNext()) != null && i++ < 100) {
      int j = 0;
      if (i != 1) {
        TaskModel taskModel = new TaskModel();
        for (String token : nextLine) {
          setModelField(taskModel, j++, token);
        }
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
        taskModel.setHColumn(Boolean.valueOf(token));
      case 8:
        taskModel.setIColumn(Boolean.valueOf(token));
      case 9:
        taskModel.setJColumn(token);
    }
  }
}
