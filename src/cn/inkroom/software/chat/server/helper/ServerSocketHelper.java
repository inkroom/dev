package cn.inkroom.software.chat.server.helper;

import cn.inkroom.software.chat.server.listener.ClientListener;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Created by 墨盒 on 17:01.
 * 服务器socket帮助类
 */
public class ServerSocketHelper {
    private ServerSocket socket;
    private Map<String, Socket> clients;

    private ClientListener listener;

    public ServerSocketHelper(int port) {
        try {
            socket = new ServerSocket(port);
            clients = new HashMap<>();
            //接收新用户加入的客户端
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Socket client = socket.accept();
                            clients.put(client.getInetAddress().getHostAddress(), client);
                            if (listener != null) {
                                listener.connect(client.getInetAddress().getHostAddress());
                            }
                            //开启接收客户端消息的线程
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    while (true) {
                                        try {
                                            System.out.println("这里是服务器，开始接受" + client.getInetAddress().getHostAddress() + " 发送的消息");
                                            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                                            String value = reader.readLine();
                                            System.out.println("这里是服务器，接受到" + client.getInetAddress().getHostAddress() + " 发送的消息 " + value);
                                            if (listener != null) {//异步处理接收到的消息
                                                new Thread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        System.out.println("这里是服务器，接收到消息+" + value);
                                                        listener.message(value);
                                                    }
                                                }).start();
                                            }
//                                            reader.close();
                                        } catch (IOException e) {
                                            if (listener != null) {
                                                listener.disconnect(client.getInetAddress().getHostAddress());
                                            }
                                            break;
                                        }
                                    }
                                }
                            }).start();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setListener(ClientListener listener) {
        this.listener = listener;
    }

    public void sendMessage(String value) {
        Set<String> keys = clients.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            sendMessage(value, iterator.next());
        }
    }

    public void sendMessage(String value, String ip) {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clients.get(ip).getOutputStream()));
            writer.write(value);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean close(String ip) {
        try {
            sendMessage("<type>1</type>",ip);
            clients.get(ip).close();
            clients.remove(ip);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
