package com.interview.interview.parse;

import com.interview.interview.model.TaskModel;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParseFile {
  private String path;

  public ParseFile(String path) {
    this.path = path;
  }

  private static void set(TaskModel taskModel, Integer j, String token) {
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

  public void parseCvs() throws Exception {
    List<TaskModel> list = new ArrayList<>();
    CSVReader reader;
    int i = 0;

    reader = new CSVReader(new FileReader(path), ',');
    String[] nextLine;

    while ((nextLine = reader.readNext()) != null && i++ < 5) {
      int j = 0;
      if (i != 1) {
        TaskModel taskModel = new TaskModel();
        for (String token : nextLine) {
          set(taskModel, j++, token);
        }
        list.add(taskModel);
      }
    }
    list.forEach(System.out::println);
    reader.close();
  }
}
