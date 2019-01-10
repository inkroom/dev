package cn.inkroom.software.version.common.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * 文件读写类
 */
public class FileTransfer {
    /**
     * 从socket流中复制文件到本地
     *
     * @param channel socket
     * @param path    路径
     * @throws IOException io
     */
    public boolean toLocalFile(SocketChannel channel, String path) throws IOException {

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 10);
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        FileChannel fileChannel = fis.getChannel();

        if (channel.isConnectionPending()) {
            int count = -1;
            while ((count = channel.read(byteBuffer)) != -1) {
                byteBuffer.flip();
                fileChannel.write(byteBuffer);
                byteBuffer.clear();
            }
            fileChannel.close();
        } else {
            return false;
        }
        return true;
    }


    /**
     * 从socket流中复制文件到本地
     *
     * @param channel socket
     * @param path    路径
     * @throws IOException io
     */
    public boolean toLocalDir(SocketChannel channel, String path) throws IOException {
        // TODO: 18-5-15 暂不考虑
        return false;
    }

    /**
     * 往socket中传输文件
     *
     * @param channel socket
     * @param file    文件
     * @throws IOException io
     */
    public boolean toSocketFile(SocketChannel channel, String file) throws IOException {
        File f = new File(file);
        if (f.exists() && f.isFile()) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 20);
            FileChannel fileChannel = new FileInputStream(file).getChannel();
            while (fileChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                channel.write(byteBuffer);
                byteBuffer.clear();
            }
            return true;
        } else {
            throw new RuntimeException("错误的文件");
        }
    }

    /**
     * 往socket中传输文件夹
     *
     * @param channel socket
     * @param file    文件
     * @throws IOException io
     */
    void toSocketDir(SocketChannel channel, String file) throws IOException {
        // TODO: 18-5-15 暂不考虑
    }
}
