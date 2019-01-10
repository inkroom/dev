package cn.inkroom.software.chat.client.helper;

import cn.inkroom.software.chat.client.listener.MessageListener;

import java.io.*;
import java.net.Socket;

/**
 * Created by 墨盒 on 16:40.
 * socket连接类
 */
public class ClientSocketHelper implements Runnable {
    private Socket socket;
    private MessageListener messageListener;

    private volatile boolean isConnect = true;

    public ClientSocketHelper(String ip, int port, MessageListener listener) {
        try {
            this.messageListener = listener;
            socket = new Socket(ip, port);

            new Thread(this).start();
        } catch (IOException e) {
//            e.printStackTrace();
            if (messageListener != null) {
                messageListener.disconnect(e);
            }
        }
    }

    public void sendMessage(String value, int type) {
        try {
            StringBuilder builder = new StringBuilder("<sender>" + socket.getInetAddress().getHostName() + "</sender><type>" + type + "</type>");
            builder.append("<msg>");
            builder.append(value);
            builder.append("</msg>");
            value += "sender:" + socket.getInetAddress().getHostName() + " " + value;
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write(builder.toString());
            writer.newLine();
            writer.flush();
//            if (messageListener != null) {
//                messageListener.sendSuccess(value);
//            }
//            writer.close();
        } catch (IOException e) {
//            e.printStackTrace();
            if (messageListener != null) {
                messageListener.sendFail(value);
            }
        }

    }

    public void setMessageListener(MessageListener messageListener) {
        this.messageListener = messageListener;
    }

    @Override
    public void run() {
        while (isConnect) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String value = reader.readLine();
                if (messageListener != null) {//异步处理接收到的消息
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("这里是客户端，收到消息：" + value);
                            messageListener.message(value);
                        }
                    }).start();
                }
//                reader.close();
            } catch (Throwable e) {
                if (messageListener != null)
                    messageListener.disconnect(e);
                break;
            }
        }
    }

    public synchronized void stop() {
        isConnect = false;
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
