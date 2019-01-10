package cn.inkroom.web.socket.entities;

import cn.inkroom.web.socket.enums.BoardStatus;

/**
 * Created by 墨盒 on 14:27.
 * 棋局entity
 */
public class Board {
    public static final int ROW_COUNT = 15;
    public static final int COLUMN_COUNT = 15;
    private int id;
    private int white = -1;
    private int black = -1;
    private String chesses;
    //    private ArrayList<Chess> chesses;
    private int now = -1;
    private BoardStatus status;

    public int getNow() {
        return now;
    }

    public void setNow(int now) {
        this.now = now;
    }

    public BoardStatus getStatus() {
        return status;
    }

    public void setStatus(BoardStatus status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWhite() {
        return white;
    }

    public void setWhite(int white) {
        this.white = white;
    }

    public int getBlack() {
        return black;
    }

    public void setBlack(int black) {
        this.black = black;
    }


    public String getChesses() {
        return chesses;
    }

    public void setChesses(String chesses) {
        this.chesses = chesses;
    }

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", white=" + white +
                ", black=" + black +
                ", chesses='" + chesses + '\'' +
                ", now=" + now +
                ", status=" + status +
                '}';
    }
}
