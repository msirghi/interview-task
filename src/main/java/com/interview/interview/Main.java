package com.interview.interview;

import com.interview.interview.parse.ParseFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

public class Main {
  private static final String path = "C:\\Users\\Михаил\\Desktop\\Interview-task-data-osh (2).csv";
  private static Logger logger = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {
    try {
      new ParseFile(path).parseCvs();
    } catch (Exception e) {
      logger.warn("Error while parsing given file.");
    }
  }
}
