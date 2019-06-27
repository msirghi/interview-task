# Interview task #
Java application that consumes a .CSV file, parses the data and inserts to database.

## Requirements ###
* Java 8

## Setup Project
* Setup Project :
    * Windows & Linux: `mvn clean install`

### Code
I am using Google [Java Style Guide](https://google.github.io/styleguide/javaguide.html):
* Import Style Code rule in your IDE import :
    * Intellij IDEA : `config/intellij-java-google-style.xml`
    * Eclipse : `config/eclipse-java-google-style.xml`
* In order to check your code you need:
    - Checkstyle plugin installed;
    - Enter CheckStyle tab, select Google Checks and run;
    
Also be sure to enable Lombok plugin:
* `File -> Settings -> Build, Execution, Deployment -> Compiler -> Enable annotation processing -> Apply`;


### Implemented:
- .CSV file parsing:
  - OpenCSV library is used;
  - row validation using library & empty columns (catching Exceptions);
  - bad rows are written in bad-data-timestamp.csv file;
  - relevant rows are inserted to database;
- Row statistics can be found in logs/app.log file;
- H2 database used(check properties & WebH2Configuration class);
- S4lfj logger used for tracking process(exceptions, etc.);
- Spring used for ability to see result in H2 database(under /console path);

### Example screenshots:
- Rows in H2:

![alt text](https://raw.githubusercontent.com/msirghi/interview-task/master/src/main/resources/screen/h2.PNG)

- Log file:

![alt text](https://raw.githubusercontent.com/msirghi/interview-task/master/src/main/resources/screen/log.PNG)

- bad-data files:

![alt text](https://raw.githubusercontent.com/msirghi/interview-task/master/src/main/resources/screen/bad-data.PNG)
