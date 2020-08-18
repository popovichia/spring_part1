package java2.chat.client;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;

import java2.chat.server.entity.Message;

public class JFXController {
    @FXML
    TextField textFieldServer;
    @FXML
    TextField textFieldPort;
    @FXML
    Label labelNickName;
    @FXML
    TextArea textAreaChat;
    @FXML
    TextArea textAreaUsersList;
    @FXML
    TextField textFieldMessage;
    @FXML
    HBox upperPanel;
    @FXML
    HBox bottomPanel;
    @FXML
    TextField textFieldLogin;
    @FXML
    PasswordField textFieldPassword;

    private Connection connection;
    public void connect() {
        this.connection = new Connection(this);
        Thread connectionThread = new Thread(this.connection);
        connectionThread.start();
    }
    public void sendAuth() {
        if (this.connection == null) {
            connect();
        }
        Message messageAuthentication = new Message(textFieldLogin.getText(),
                "server", "Authentication", textFieldPassword.getText());
        this.connection.sendContent(messageAuthentication);
        textFieldLogin.clear();
        textFieldPassword.clear();
    }
    public void sendMessage() {
        Message message = null;
        if (textFieldMessage.getText().startsWith("/")) {
            message = new Message(connection.getUser().getNickName(),
                    "", "Message", textFieldMessage.getText());
        } else {
            message = new Message(connection.getUser().getNickName(),
                    "", "Message", textFieldMessage.getText());            
        }
        this.connection.sendContent(message);
        textFieldMessage.clear();
    }
    public void changeUI(boolean isAuthorized) {
        upperPanel.setVisible(!isAuthorized);
        upperPanel.setManaged(!isAuthorized);
        bottomPanel.setVisible(isAuthorized);
        bottomPanel.setManaged(isAuthorized);
    }
}
