package cn.inkroom.web.excel.bean;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/12/13
 * @Time 15:41
 * @Descorption
 */
public class Team {
    private String id;
    private String name;
    private String username;
    private String org;
    private String password = "e10adc3949ba59abbe56e057f20f883e";

    @Override
    public String toString() {
        return "Team{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", org='" + org + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
