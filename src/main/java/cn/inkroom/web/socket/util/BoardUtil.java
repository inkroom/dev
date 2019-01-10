package cn.inkroom.web.socket.util;


import cn.inkroom.web.socket.entities.Board;

/**
 * Created by 墨盒 on 19:48.
 * 棋盘工具类
 * flag为true为白子
 */
public class BoardUtil {
    private Boolean board[][];
    private int player = 0;

    public BoardUtil() {
    }

//    public BoardUtil(String value) {
//        init(value);
//    }

    public void init(String value, int userId) {
        board = JsonUtil.splitChess(value, userId);
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
        System.out.println("现在玩家人数 = " + player);
    }

    public Boolean addChess(boolean flag, int x, int y) throws IllegalAccessException {
        if (board[x][y] != null) {
            throw new IllegalAccessException("该位置已有棋子！");
        } else {
            board[x][y] = flag;
        }
        return check(flag, x, y);
    }

    /**
     * 判断输赢
     *
     * @param flag 当前落子的颜色，true为白色
     * @param x    坐标
     * @param y    坐标
     * @return 如果当前颜色胜利，则返回true，否则返回false
     */
    public boolean check(boolean flag, int x, int y) {
        int count = 1;
        //横向，向右
        for (int i = x + 1; i <= x + 4 && i < Board.COLUMN_COUNT; i++) {
            System.out.println("i = " + i + " y = " + y + " flag = " + flag + "  board = " + board[i][y]);
            if (board[i][y] != null && board[i][y] == flag) {
                count++;
            } else {
                break;
            }
        }
        //横向，往左
        for (int i = x - 1; i >= 0 && i >= x - 4; i--) {
            System.out.println("i = " + i + " y = " + y + " flag = " + flag + "  board = " + board[i][y]);
            if (board[i][y] != null && board[i][y] == flag) {
                count++;
            } else {
                break;
            }
        }
        System.out.println("横向 count = " + count);
        if (count >= 5) {
            return true;
        }
        //纵向，往上
        for (int i = y - 1; i <= y - 4  && i >= 0; i--) {
            if (board[x][i] != null && board[x][i] == flag) {
                count++;
            } else {
                break;
            }
        }
        //纵向，往下
        for (int i = y + 1; i <= y + 4 && i < Board.ROW_COUNT; i++) {
            if (board[x][i] != null && board[x][i] == flag) {
                count++;
            } else {
                break;
            }
        }
        if (count >= 5) {
            return true;
        }
        System.out.println("纵向 count = " + count);
        //西南方向,
        for (int i = x - 4, j = y - 4; i < x + 4 && i < Board.COLUMN_COUNT && i >= 0 && j < y + 4 && j < Board.ROW_COUNT && j >= 0; i++, j++) {
            if (board[i][y] != null && board[i][y] == flag) {
                count++;
            } else {
                break;
            }
        }
        if (count >= 5) {
            return true;
        }
        System.out.println("西南向 count = " + count);
        //东北方向,
        for (int i = x - 4, j = y + 4; i < x + 4 && i < Board.COLUMN_COUNT && i >= 0 && j > y - 4 && j < Board.ROW_COUNT && j >= 0; i++, j--) {
            if (board[i][y] != null && board[i][y] == flag) {
                count++;
            } else {
                break;
            }
        }
        System.out.println("东北向 count = " + count);
        return count >= 5;
    }

    public void clear() {
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[i].length; j++) {
//                board[i][j] = null;
//            }
//        }
//        setPlayer(getPlayer() == 0 ? 0 : getPlayer() - 1);
    }

}
