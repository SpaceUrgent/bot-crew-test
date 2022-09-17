# Application with console interface

Simple Spring Boot application with console interface. After connecting to database you will be able to retrive statics about university by entering the request to console.

# Console inputs

Run the app. After the "Please enter input here. To shut down the application type 'Close'" appears </br>
 you can enter and execute following requests:
- Who is head of department {department name}
- Show {department name} statistics
- Show the average salary for the department {department name}
- Show count of employee for {department name}
- Global search by {pattern}
- Close

<sub>*you can add dot or question mark at the end of request. 
</sub>

# Installation

1. Clone the project to your machine
2. In src/main/resources/application.properties set following properties:
- spring.datasource.url=DATABASE_URL
- spring.datasource.username=YOUR_USERNAME
- spring.datasource.password=YOUR_PASSWORD
- spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
- spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
- spring.h2.console.enabled=false
- spring.jpa.hibernate.ddl-auto=create-drop
3. In case if you don't have test data in your DB you can uncomment annotaion @Component above </br>
src/main/java/com/test/task/data/injector/DummyDataInjector.java </br>
(in this case the dummy test data will be save to your DB during BeanFactory creates beans)
4. Execute comman "mvn clean install" in terminal
5. Run the app