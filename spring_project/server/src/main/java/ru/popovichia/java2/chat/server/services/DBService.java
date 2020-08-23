/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.popovichia.java2.chat.server.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.beans.factory.annotation.Autowired;

public class DBService {
    
    @Autowired
    private Connection connection;

    private Statement statement;
    
    public boolean initTable(String sqlRequest) {
        boolean result = false;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sqlRequest);
            result = true;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result;
    }
}