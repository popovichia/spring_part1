/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2.chat.server.entity;

import java.io.Serializable;

/**
 *
 * @author igor
 */
public class Message implements Serializable {
    private String userFrom;
    private String userTo;
    private String title;
    private String body;

    public Message(String userFrom, String userTo, String title, String body) {
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.title = title;
        this.body = body;
    }
    public String getUserFrom() {
        return userFrom;
    }

    public String getUserTo() {
        return userTo;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
