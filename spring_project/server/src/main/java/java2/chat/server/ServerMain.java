package java2.chat.server;

import java2.chat.server.services.ClientService;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java2.chat.server.entity.Command;
import java2.chat.server.entity.Message;
import java2.chat.server.entity.User;
import java2.chat.server.services.DBService;
import java2.chat.server.services.UserService;

public class ServerMain implements Runnable {
    private ArrayList<ClientService> clientsServices;
    private ArrayList<User> listAllUsers;
    private ServerSocket serverSocket;
    private Socket socket;
    private int port;
    private JFXController jfxController;
    public ServerMain(int port, JFXController jfxController) {
        this.clientsServices = new ArrayList<>();
        this.listAllUsers = new ArrayList<>();
        this.port = port;
        this.jfxController = jfxController;
        this.jfxController.writeLog("Сервер запущен.\n"
                + "Для подключения к серверу, используйте параметры:\n"
                + "Сервер - localhost, порт - " + port);
        initilizeDB();
        this.jfxController.writeLog("База данных инициализирована.");
    }
    public void initilizeDB() {
        String sqlRequestUsers =
                "CREATE TABLE IF NOT EXISTS users ( "
                + "id INTEGER NOT NULL PRIMARY KEY, "
                + "login TEXT NOT NULL, "
                + "password TEXT NOT NULL, "
                + "nickname TEXT NOT NULL, "
                + "fullname TEXT);";
        String sqlRequestBlackList =
                "CREATE TABLE IF NOT EXISTS blacklist ( "
                + "id INTEGER NOT NULL Primary key, "
                + "user_id INTEGER NOT NULL, "
                + "block_user_id INTEGER NOT NULL);";
        DBService.connect();
        DBService.checkTable(sqlRequestUsers);
        DBService.checkTable(sqlRequestBlackList);
        DBService.disconnect();
    }
    @Override
    public void run() {
        try {
            this.serverSocket = new ServerSocket(port);
            while (true) {
                this.socket = serverSocket.accept();
                ClientService clientService = new ClientService(this, socket, this.jfxController);
                Thread clientServiceThread = new Thread(clientService);
                clientServiceThread.start();
                this.jfxController.writeLog("Подключился клиент." + socket);
                UserService.connect();
                listAllUsers = UserService.getListAllUsers();
                UserService.disconnect();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (Exception exception){
            exception.printStackTrace();
        } finally {
            try {
                if(socket != null) {
                    socket.close();
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            try {
                if(socket != null) {
                    serverSocket.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }
    public void stopServer() {
        try {
            if(this.socket != null) {
                this.socket.close();
            }
            if(this.serverSocket != null) {
                this.serverSocket.close();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        this.jfxController.writeLog("Сервер остановлен.");
    }
    public void subscribe(ClientService clientService) {
        clientsServices.add(clientService);
    }
    public void unsubscribe(ClientService clientService) {
        clientsServices.remove(clientService);
    }
    public boolean isLoggedIn(String nickName) {
        boolean result = false;
        for (ClientService cs : clientsServices) {
            if (nickName.equals(cs.getUser().getNickName())) {
                result = true;
            }
        }
        return result;
    }
    public void sendContent(Message message) {
        for (ClientService cs : clientsServices) {
            if (!cs.getUser().getBlackList().contains(message.getUserFrom())
                    && message.getUserTo().equals("All")) {
                cs.sendContent(message);
            }
        }        
    }
    public void addUserToBlackList(User user, String blockedUser) {
        if (!user.getBlackList().contains(blockedUser)) {
            user.addToBlackList(blockedUser);
            UserService.connect();
            UserService.saveInBlackListByNickName(user, blockedUser);
            UserService.disconnect();
        }
    }
}
