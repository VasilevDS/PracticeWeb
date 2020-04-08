package ru.vasilev;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.vasilev.connection.ConnectionManager;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        // check connect BD
        ConnectionManager connectionManager = new ConnectionManager();
        System.out.println(connectionManager.getConnection());
        SpringApplication.run(App.class, args);
    }
}
