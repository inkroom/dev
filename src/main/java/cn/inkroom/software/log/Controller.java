package cn.inkroom.software.log;

import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 墨盒
 */
public class Controller extends Thread {
    private int max;
    private Helper helper;
    private Queue queue;
    private LogSocket socket;
    private Pattern pattern = Pattern.compile("([0-9]+)-(.*)");

    public Controller(int port, int max) throws IOException {

        socket = new LogSocket(this, port);
        queue = new Queue();
        this.max = max;
    }

    public void start() {
        socket.start();
    }

    public void handMessage(String line) {
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            Message m = new Message(Long.parseLong(matcher.group(1)), matcher.group(2));
            queue.add(m);
            if (helper == null) {
                helper = new Helper();
                helper.start();
            }
        }
    }

    private class Helper extends Thread {
        @Override
        public void run() {
            synchronized (LinkedList.class) {
                try {
                    Thread.sleep(max);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.println();
            }
            helper = null;
        }
    }
}
