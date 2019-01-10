package cn.inkroom.software.connect;

import java.io.*;

import cn.inkroom.software.entity.FtpFile;
import sun.net.www.protocol.ftp.FtpURLConnection;

import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by 墨盒 on 16:14.
 */
public class FtpConnect {
    private String path;
    public ArrayList<FtpFile> files;

    public FtpConnect(String path) {
        this.path = encode(path);
//        this.path = path;
        System.out.println(path + "   " + this.path);
        files = new ArrayList<>();
    }

    public ArrayList<FtpFile> getFileList() {
        try {
//            String temp1 = URLEncoder.encode(path, "gbk");
//            System.out.println(temp1);
//            System.out.println(URLDecoder.decode(temp1, "gbk"));
//            System.out.println(URLDecoder.decode(path, "gbk"));
//            System.out.println(encode(path));
            URL url = new URL(path);
            System.out.println("path  =  " + url.getPath());
//            ((FtpURLConnection) url.openConnection()).setContentType("charset=gbk");
//            System.out.println(url.openConnection().getClass());
//            FtpURLConnection connection = (FtpURLConnection) url.openConnection();
            System.out.println(url.openStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "gbk"));
//ftp://ftp.cs.nsu.edu.cn/%A1%B0%B2%A9%CA%C0%B1%AD%A1%B1%CE%EF%C1%AA%CD%F8%B4%B4%D2%E2%C9%E8%BC%C6%B4%F3%C8%FC/
            String temp;
            while ((temp = reader.readLine()) != null) {
                FtpFile file = anaLine(temp);
//                System.out.println(temp);
//                System.out.println(file);
                files.add(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return files;
    }

    public void download(String filePath) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(path);
                    FileOutputStream output = new FileOutputStream(filePath);

                    byte bytes[] = new byte[4 * 1024];
                    int length;
                    InputStream input = url.openStream();
                    while ((length = input.read(bytes)) != -1) {
                        output.write(bytes, 0, length);
                    }
                    input.close();
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    private FtpFile anaLine(String line) {
        String values[] = line.split(" +");
        if (values.length == 4) {
            return new FtpFile(this.path + "/" + values[3], !values[2].equals("<DIR>"));
        } else if (values.length > 4) {//文件名带有空格
            return new FtpFile(this.path + "/" + line.substring(line.indexOf(values[3])), !values[2].equals("<DIR>"));
        }
        return null;
    }

    private String encode(String value) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < value.length(); i++) {
//            System.out.println(value.charAt(i) + "   =  " + ((int) value.charAt(i)));
            if (((int) value.charAt(i)) < 127) {
                builder.append(value.charAt(i));
            } else {
                try {
                    builder.append(URLEncoder.encode(value.charAt(i) + "", "gbk"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return builder.toString();
    }

}
