package ru.popovichia.java2.chat.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class JFXController {
    
    @FXML
    HBox hboxStart;
    @FXML
    HBox hboxStop;
    @FXML
    TextField textFieldPort;
    @FXML
    TextArea textAreaServerLog;
    
    private ServerMain serverMain;
    private File fileServerLog;
    
    public void initialize() {
        File dirServerLog = new File(".");
        File[] listServerLogs = dirServerLog.listFiles();
        Arrays.sort(listServerLogs);
        int lastPagesCount = 2;
        for (File file : listServerLogs) {
            byte[] buffer = new byte[8192];
            if (file.getName().startsWith("log") 
                    && file.getName().endsWith(".log")
                    && file.canRead()) {                
                try (FileInputStream fileInputStream = new FileInputStream(file)) {
                    loadLog(fileInputStream, file, buffer, lastPagesCount);
                } catch(Exception ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        Date nowDate = new Date();
        fileServerLog = new File("log_" + nowDate.getTime() + ".log");
        try {
            fileServerLog.createNewFile();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }        
    }
    public void startServer() {
        try {
            changeUI(true);
            int port = Integer.valueOf(textFieldPort.getText());
            this.serverMain = new ServerMain(port, this);
            Thread serverMainThread = new Thread(this.serverMain);
            serverMainThread.start();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    public void stopServer() {
        try {
            changeUI(false);
            this.serverMain.stopServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void changeUI(boolean isServerStarted) {
        hboxStart.setManaged(!isServerStarted);
        hboxStart.setVisible(!isServerStarted);
        hboxStop.setManaged(isServerStarted);
        hboxStop.setVisible(isServerStarted);
    }
    public void writeLog(String message) {
        message = message + "\n";
        textAreaServerLog.appendText(message);
        byte[] dataOut = message.getBytes();
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileServerLog, true)) {
            fileOutputStream.write(dataOut);
        } catch(Exception ioException){
            ioException.printStackTrace();
        }
    }
    public void loadLog(FileInputStream fileInputStream, File file, byte[] buffer, int lastPagesCount) throws IOException {
        textAreaServerLog.appendText("Start file: " + file.getName() + " ---------------------------\n");        
        long skeepedBуtes;
        int redBytes;
        if ((fileInputStream.available() / buffer.length) > lastPagesCount) {
            skeepedBуtes = ((fileInputStream.available() / buffer.length) - lastPagesCount) * buffer.length;
            fileInputStream.skip(skeepedBуtes);
            redBytes = fileInputStream.available();
            while (fileInputStream.read(buffer) > 0) {
                textAreaServerLog.appendText(new String(buffer, "UTF-8"));
                buffer = new byte[buffer.length];
            }
        } else {
            redBytes = fileInputStream.available();
            while (fileInputStream.read(buffer) > 0) {
                textAreaServerLog.appendText(new String(buffer, "UTF-8"));
                buffer = new byte[buffer.length];
            }
        }
        textAreaServerLog.appendText("\nEnd file: " + file.getName() + " --------------------------- size: " + redBytes + "\n");        
    }
}
