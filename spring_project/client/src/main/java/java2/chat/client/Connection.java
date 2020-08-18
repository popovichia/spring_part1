/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2.chat.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java2.chat.server.entity.Message;
import java2.chat.server.entity.User;

/**
 *
 * @author igor
 */
public class Connection implements Runnable {
    private JFXController jfxController;
    private String serverAddress;
    private int port;
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private ObjectOutputStream objectOutputStream;
    private User user;
    public Connection(JFXController jfxController) {
        try {
            this.jfxController = jfxController;
            this.serverAddress = jfxController.textFieldServer.getText();
            this.port = Integer.valueOf(jfxController.textFieldPort.getText());
            this.socket = new Socket(this.serverAddress, this.port);
            this.inputStream = this.socket.getInputStream();
            this.outputStream = this.socket.getOutputStream();
            this.objectOutputStream = new ObjectOutputStream(outputStream);
        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            try {
                inputStream.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            while (true) {
                if (inputStream.available() > 0) {
                    Object receivedObject = objectInputStream.readObject();
                    if (receivedObject instanceof User) {
                        user = (User) receivedObject;
                        if (user != null) {
                            jfxController.changeUI(true);
                            jfxController.textAreaChat.appendText("Вы вошли в сeть под именем:\n"
                                    + "\t" + user.getNickName() + "\n");
                        }
                    }
                    if (receivedObject instanceof Message) {
                        Message message = (Message) receivedObject;
                        if (message.getTitle().equals("UsersList")) {
                            
                        } else {
                            showMessage(message);
                        }
                    }
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            try {
                socket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            jfxController.changeUI(false);
        }
    }
    public User getUser() {
        return user;
    }
    public void sendContent(Message message) {
        try {
            objectOutputStream.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }
    private void showMessage(Message message) {
        jfxController.textAreaChat.appendText(
                "From: " + message.getUserFrom() + "\n" +
                "\tTo: " + message.getUserTo() + "\n" +
                "\tTitle: " + message.getTitle() + "\n" +
                "\tBody: " + message.getBody() + "\n");
    }
}