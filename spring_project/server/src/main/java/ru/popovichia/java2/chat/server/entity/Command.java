/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.popovichia.java2.chat.server.entity;

import java.io.Serializable;

/**
 *
 * @author igor
 */
public class Command implements Serializable{
    private String userFrom;
    private String command;
    public Command(String userFrom, String command) {
        this.userFrom = userFrom;
        this.command = command;
    }
    public String getUserFrom() {
        return userFrom;
    }
    public String getCommand() {
        return command;
    }
}
