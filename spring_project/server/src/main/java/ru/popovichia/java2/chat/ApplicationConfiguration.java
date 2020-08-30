package ru.popovichia.java2.chat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.popovichia.java2.chat.server.JFXController;
import ru.popovichia.java2.chat.server.ServerMain;
import ru.popovichia.java2.chat.server.services.DBService;

@Configuration
@ComponentScan(basePackages = "ru.popovichia.java2.chat")
public class ApplicationConfiguration {
  
    @Bean
    public Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:main.db");
    }

    @Bean
    public DBService dbService() {;
        return new DBService();
    }

//    @Bean
//    public ServerMain serverMain(int port, JFXController jfxController) {;
//        return new ServerMain(port, jfxController);
//    }
    
}
