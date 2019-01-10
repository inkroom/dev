package cn.inkroom.web.teach.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.*;

public class ContextListener implements ServletContextListener {
    String[] keys = new String[]{"content", "prev", "next", "title", "url"};


    String path = "/read.txt";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        try {
            String base = servletContextEvent.getServletContext().getRealPath(path);
            File file = new File(base);
            if (file.exists()) {
                BufferedReader in = new BufferedReader(new FileReader(file));
                String temp;
                for (int i = 0; i < keys.length; i++) {
                    temp = in.readLine();
                    servletContextEvent.getServletContext().setAttribute(keys[i], temp);
                }
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

        try {
            String base = servletContextEvent.getServletContext().getRealPath(path);
            File file = new File(base);
            if (file.exists()) {
                BufferedWriter out = new BufferedWriter(new FileWriter(file));
                for (int i = 0; i < keys.length; i++) {
                    out.write(servletContextEvent.getServletContext().getAttribute(keys[i]).toString());
                    out.newLine();
                }
                out.close();
            }
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }
}
