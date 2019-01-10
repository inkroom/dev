package cn.inkroom.software.update;

import cn.inkroom.software.update.common.message.writer.XmlMessageWriter;
import cn.inkroom.software.update.server.version.VersionController;
import cn.inkroom.software.update.server.version.map.DirectoryVersion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServerEntry {
    public static void main(String[] args) {

        List<DirectoryVersion> v = new ArrayList<>();
        DirectoryVersion directoryVersion = new DirectoryVersion("test", "/media/inkbox/study/study/后端/output/kill", 1);
        v.add(directoryVersion);

        VersionController controller = new VersionController(9999, 9990, false, v);
        controller.setWriter(new XmlMessageWriter());
        while (true){
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            if (line.equals("1")){
                System.out.println("开始广播");
                controller.broadVersion();
            }
        }
    }
}
