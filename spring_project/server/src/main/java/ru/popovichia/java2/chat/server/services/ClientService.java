package ru.popovichia.java2.chat.server.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import ru.popovichia.java2.chat.server.JFXController;
import ru.popovichia.java2.chat.server.ServerMain;
import ru.popovichia.java2.chat.server.entity.Command;
import ru.popovichia.java2.chat.server.entity.Message;
import ru.popovichia.java2.chat.server.entity.User;

public class ClientService implements Runnable {
    private ServerMain serverMain;
    private Socket socket;
    private JFXController jfxController;
    private InputStream inputStream;
    private OutputStream outputStream;
    private ObjectOutputStream objectOutputStream;
    private User user;
    public ClientService(ServerMain serverMain, Socket socket, JFXController jfxController) {
        try {
            this.serverMain = serverMain;
            this.socket = socket;
            this.jfxController = jfxController;
            this.inputStream = this.socket.getInputStream();
            this.outputStream = this.socket.getOutputStream();
            this.objectOutputStream = new ObjectOutputStream(outputStream);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    @Override
    public void run() {
        waitContentFromClient();
    }
//                    if (messageArray[0].startsWith("/")) {
//                        if(messageArray[0].equals("/tonick")) {
//                            if (messageArray.length > 2 && serverMain.isLoggedIn(messageArray[1])) {
//                                serverMain.sendMsg(this, messageArray[1], messageArray[2]);
//                            }
//                        }
//                        if(messageArray[0].equals("/block")) {
//                            if (messageArray.length == 2) {
//                                serverMain.addUserToBlackList(this.getUser(), messageArray[1]);
//                                this.jfxController.writeLog("Чёрный список пользователя "
//                                        + this.getUser().getNickName() + ": "
//                                        + this.getUser().getBlackList().toString());
//                            }                        
//                        }
//                        if (messageFromClient.equals("/end")) {
//                            dataOutputStream.writeUTF("/serverClosed");
//                            return;
//                        }
//                    String nickNameTo = commandArray[2];
//                    if (commandArray.length > 2 && commandArray[0].equals("/w")) {
//                        if (serverMain.isLoggedIn(nickNameTo)) {
//                            String message = "";
//                            for (int i = 2; i < commandArray.length; i++) {
//                                message += commandArray[i] + " ";
//                            }
//                            serverMain.sendMsg(ClientService.this, message);
//                        } else {
//                            sendMsg("Нет в сети пользователя с ником: " + commandArray[1]);
//                        }
//                    } else if (commandArray[0].equals("/w") && commandArray.length < 3) {
//                        sendMsg("Ошибка при вводе команды. Введите: /w nick сообщение. Для отправки личного сообщения.");
//                    } else {
//                        serverMain.sendMsg(nickNameTo + ": " + messageFromClient);
//                    }
//                    } else {
//                        serverMain.sendMsg(this, messageFromClient);
//                    }
//                }
//            }
    private void waitContentFromClient() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            while (true) {
                if (this.inputStream.available() > 0) {
                    Object receivedObject = objectInputStream.readObject();
                    if (receivedObject instanceof Message) {
                        Message message = (Message) receivedObject;
                        if (message.getTitle().equals("Authentication")) {
                            if (message.getUserFrom().isEmpty()) {
                                Message messageError = new Message("server",
                                        "", "Error", "Введите имя пользователя.");
                                serverMain.sendContent(messageError);                                
                            } else if (message.getBody().isEmpty()) {
                                Message messageError = new Message("server",
                                        "", "Error", "Введите Пароль.");
                                serverMain.sendContent(messageError);
                            } else {
                                AuthService.connect();
                                String nickName = AuthService.getNickByLoginAndPassword(
                                        message.getUserFrom(), message.getBody());
                                AuthService.disconnect();
                                if (nickName == null) {
                                    Message messageError = new Message("server",
                                            "", "Error", "Неверное имя пользователя или пароль.");
                                    serverMain.sendContent(messageError);
                                } else if (serverMain.isLoggedIn(nickName)) {
                                    Message messageError = new Message("server",
                                            "", "Error", "Данный пользователь уже в сети.");
                                    serverMain.sendContent(messageError);                                    
                                } else {
                                    this.serverMain.subscribe(ClientService.this);
                                    UserService.connect();
                                    int id = Integer.valueOf(UserService.getFieldValueByNickName("id", nickName));
                                    String login = UserService.getFieldValueByNickName("login", nickName);
                                    String fullName = UserService.getFieldValueByNickName("fullname", nickName);
                                    ArrayList<String> blackList = UserService.getBlackListByNickName(nickName);
                                    UserService.disconnect();
                                    ClientService.this.user = new User(id, login, nickName, fullName, blackList);
                                    sendContent(user);
                                    ClientService.this.jfxController.writeLog("Вошёл пользователь: "
                                            + ClientService.this.user.getNickName() + "; "
                                            + "чёрный список пользователя: "
                                            + ClientService.this.user.getBlackList().toString());
                                }                           
                            }
                        } else if (message.getTitle().equals("UsersList")) {
                            serverMain.sendContent(message);
                        } else if (message.getTitle().equals("Message")
                                && message.getUserTo().isEmpty()) {
                            serverMain.sendContent(message);
                        } else if (message.getTitle().equals("Message")
                                && !message.getUserTo().isEmpty()) {
                            serverMain.sendContent(message);
                        }
                    } if (receivedObject instanceof Command) {
                        //serverMain.sendContent(command);
                    }
                }
            }
        } catch(Exception exception) {
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
            serverMain.unsubscribe(ClientService.this);
            AuthService.disconnect();
        }
        
    }
    public void sendContent(Object object) {
        try {
            objectOutputStream.writeObject(object);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    public User getUser() {
        return this.user;
    }
}
