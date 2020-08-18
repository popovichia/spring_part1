/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2.chat.server.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java2.chat.server.entity.User;

/**
 *
 * @author igor
 */
public class UserService {
    private static Connection connection;
    private static Statement statement;
    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:main.db");
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<User> getListAllUsers(){
        ArrayList<User> resultList = new ArrayList<>();
        String sql = "SELECT * FROM users;";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String login = resultSet.getString(2);
                String nickName = resultSet.getString(3);
                String fullName = resultSet.getString(4);
                User user = new User(id, login, nickName, fullName);
                resultList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return resultList;
        
    }
    public static String getFieldValueByNickName(String fieldName, String nickName) {
        String resultString = null;
        String sql = String.format("SELECT %s FROM users where nickname = '%s';", fieldName, nickName);
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next() && fieldName.equals("id")) {
                resultString = String.valueOf(resultSet.getInt(1));
            } else {
                resultString = resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return resultString;
        }
        return resultString;
    }
    public static ArrayList<String> getBlackListByNickName(String nickName) {
        ArrayList<String> resultList = new ArrayList<>();
        String sql = String.format(
                "SELECT ublocked.nickname FROM blacklist AS b "
                + "INNER JOIN users AS uowner ON b.user_id=uowner.id "
                + "INNER JOIN users AS ublocked ON b.block_user_id=ublocked.id "
                + "WHERE uowner.nickname ='%s'",
                nickName);
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                resultList.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }
    public static int getRowId() {
        int result = 0;
        String sql = "SELECT MAX(id) FROM blacklist;";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static void saveInBlackListByNickName(User blackListOwner, String nickName) {
        int rowId = getRowId() + 1;
        int userId = Integer.valueOf(getFieldValueByNickName("id", nickName));
        String sqlInsert = String.format(
                "INSERT INTO blacklist(id, user_id, block_user_id) VALUES(%d, %d, %d);",
                rowId, blackListOwner.getId(), userId);
        try {
            statement.executeUpdate(sqlInsert);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteUserFromBlackList(User blackListOwner, String nickName) {
        int userId = Integer.valueOf(getFieldValueByNickName("id", nickName));
        String sqlInsert = String.format(
                "DELETE FROM blacklist WHERE id, user_id = %d and block_user_id = %d;",
                blackListOwner.getId(), userId);
        try {
            statement.executeUpdate(sqlInsert);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updateNickName(User user, String newNickName) {
        String sqlUpdate = String.format(
                "UPDATE users SET = '%s' nickname = WHERE id = %d",
                newNickName, user.getId());
        try {
            statement.executeUpdate(sqlUpdate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    
}
