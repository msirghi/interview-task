package com.interview.interview;

import com.interview.interview.parse.ParseFile;
import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application {
  private static final String path = new File("").getAbsolutePath()
          + "\\src\\main\\resources\\Interview-task-data-osh (2).csv";
  private static Logger logger = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    new SpringApplication(Application.class).run(args);
    try {
      new ParseFile(path).parseCvs();
    } catch (Exception e) {
      logger.error("Error while parsing the file.");
    }
  }

}
