/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2.chat.server.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author igor
 */
public class User implements Serializable {
    private int id;
    private String login;
    private String nickName;
    private String fullName;
    private ArrayList<String> blackList;

    public User(int id, String login, String nickName, String fullName, ArrayList<String> blackList) {
        this.id = id;
        this.login = login;
        this.nickName = nickName;
        this.fullName = fullName;
        this.blackList = blackList;       
    }
    public User(int id, String login, String nickName, String fullName) {
        this.id = id;
        this.login = login;
        this.nickName = nickName;
        this.fullName = fullName;
        this.blackList = new ArrayList<>();
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getLogin() {
        return this.login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getNickName() {
        return this.nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getFullName() {
        return this.fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void addToBlackList(String nickName) {
        this.blackList.add(nickName);
    }
    public ArrayList<String> getBlackList() {
        return this.blackList;
    }
    @Override
    public String toString() {
        return this.id + ", " + this.login + ", " + this.nickName + ", " + this.fullName;
    }
}
