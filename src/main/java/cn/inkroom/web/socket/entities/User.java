package cn.inkroom.web.socket.entities;

import cn.inkroom.web.socket.enums.UserStatus;

/**
 * Created by 墨盒 on 14:26.
 * 用户类
 */
public class User {
    private int id;
    private String account;
    private String password;
    private UserStatus status;
    private int boardId = -1;

    public User() {
    }

    public User(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", boardId=" + boardId +
                '}';
    }
}
