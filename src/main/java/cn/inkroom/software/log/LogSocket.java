package cn.inkroom.software.log;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 墨盒
 */
public class LogSocket extends Thread {

    private Controller controller;

    private ServerSocket server;

    private Logger logger = Logger.getLogger(getClass());

    public LogSocket(Controller c, int port) throws IOException {
        this.controller = c;
        server = new ServerSocket(port);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket client = server.accept();
                new Thread(() -> {
                    BufferedReader reader = null;
                    try {
                        reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    } catch (IOException e) {
                        e.printStackTrace();
                        try {
                            client.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                    if (reader == null) return;
                    while (true) {
                        try {
                            String line = null;
                            while ((line = reader.readLine()) != null) {

                                controller.handMessage(line);
                            }
                        } catch (IOException e) {
                            break;
                        } finally {
                            try {
                                if (client != null)
                                    client.close();
                                reader.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }).start();

            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }

        }
    }
}
