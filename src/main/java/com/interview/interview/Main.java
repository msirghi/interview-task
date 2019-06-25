package com.interview.interview;

import com.interview.interview.dto.TaskEntity;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;

public class Main {

  public static TaskEntity set(TaskEntity taskEntity, Integer j, String token) {
    switch (j) {
      case 0:
        taskEntity.setAColumn(token);
      case 1:
        taskEntity.setBColumn(token);
      case 2:
        taskEntity.setCColumn(token);
      case 3:
        taskEntity.setDColumn(token);
      case 4:
        taskEntity.setEColumn(token);
      case 5:
        taskEntity.setFColumn(token);
      case 6:
        taskEntity.setGColumn(token);
      case 7:
        taskEntity.setHColumn(Boolean.valueOf(token));
      case 8:
        taskEntity.setIColumn(Boolean.valueOf(token));
      case 9:
        taskEntity.setJColumn(token);
    }
    return taskEntity;
  }

  public static void main(String[] args) {
    TaskEntity taskEntity = new TaskEntity();
    CSVReader reader = null;
    int i = 0;

    try {
      reader = new CSVReader(
              new FileReader("C:\\Users\\Михаил\\Desktop\\Interview-task-data-osh (2).csv"), ',');
      String[] nextLine;
      Integer j = 0;

      while ((nextLine = reader.readNext()) != null && i++ < 2) {
        for (String token : nextLine) {
          set(taskEntity, j++, token);
        }
        j = 0;
      }
      System.out.println(taskEntity);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        reader.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}