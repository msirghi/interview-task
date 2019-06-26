package com.interview.interview;

import com.interview.interview.parse.ParseFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootApplication
public class Main {
  private static final String path = "C:\\Users\\Михаил\\Desktop\\Interview-task-data-osh (2).csv";
  private static Logger logger = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(Main.class);
    ApplicationContext context = app.run(args);
    try {
      new ParseFile(path).parseCvs();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
