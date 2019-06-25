package com.interview.interview;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
//    public static void main(String[] args) throws FileNotFoundException {
//    Scanner scanner
//            = new Scanner(new File("C:\\Users\\Михаил\\Desktop\\Interview-task-data-osh (2).csv"));
//    scanner.useDelimiter(",");
//    int i = 0;
//    while (scanner.hasNext()) {
//      System.out.print(scanner.next() + "|");
//      System.out.println();
//    }
//    scanner.close();
//  }

  public static void main(String[] args) {
    CSVReader reader = null;
    try {
      //Get the CSVReader instance with specifying the delimiter to be used
      reader = new CSVReader(new FileReader("C:\\Users\\Михаил\\Desktop\\Interview-task-data-osh (2).csv"), ',');
      String[] nextLine;
    int i = 0;
      while ((nextLine = reader.readNext()) != null) {
        for (String token : nextLine) {
          System.out.println(token);
        }
      }
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