package cn.inkroom.software.entity;

/**
 * Created by 墨盒 on 16:30.
 * ftp上的文件类
 */
public class FtpFile {
    private String path;
    private boolean file;

    public FtpFile(String path, boolean isFile) {
        this.path = path;
        this.file = isFile;
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return this.path.substring(this.path.lastIndexOf("/") + 1);
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isFile() {
        return file;
    }

    public void setFile(boolean file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "FtpFile{" +
                "path='" + path + '\'' +
                ", file=" + file +
                '}';
    }
}
