package cn.inkroom.web.socket.services;

import cn.inkroom.web.socket.daos.BoardDao;
import cn.inkroom.web.socket.entities.Board;
import cn.inkroom.web.socket.enums.BoardStatus;
import cn.inkroom.web.socket.enums.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 墨盒 on 14:23.
 * 棋局service
 * <p>提供落子，加入棋局 操作</p>
 */
@Service
public class BoardService {

    @Autowired
    private BoardDao boardDao;

    public List<Board> listBoard() {
        try {
            return boardDao.selectBoard(BoardStatus.WAITING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建或加入棋局
     * <p>创建棋局者执白子，后加入执黑子</p>
     *
     * @param userId  创建或加入棋局的玩家Id
     * @param boardId 创建棋局时为-1，否则为要加入棋局的id
     * @return 成功返回棋局，否则返回null
     */
    public Board createOrJoinBoard(int userId, int boardId) {
        try {
            if (boardId == -1) {//创建棋局
                int newBoardId = boardDao.createBoard(userId);
                if (newBoardId == -1) {//创建失败
                    return null;
                }
                Board board = new Board();
                board.setId(newBoardId);
                board.setWhite(userId);
                return board;

            } else {//加入棋局
//               Board board = boardDao.joinBoard(userId, boardId);
//                if (newBoardId != -1) {
//                    Board board = new Board();
//                    board.setId(newBoardId);
//                    board.setWhite(userId);
//                    return board;
//                }
                return boardDao.joinBoard(userId, boardId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 落子
     *
     * @param x       棋子的x坐标
     * @param y       棋子的y坐标
     * @param boardId 棋局id
     * @return 成功返回实例Board，否则返回null
     * @see UserStatus
     */
    public Board insertChess(int x, int y, int userId, int boardId, int otherId) {
        String chess = "{\"x\":" + x + ",\"y\":" + y + ",\"id\":" + userId + "}";
        try {
            return boardDao.insertChess(chess, boardId, otherId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Board getBoard(int boardId) {
        try {
            return boardDao.selectOneBoard(boardId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int updateBoardAndUserStatus(int boardId, BoardStatus status) {
        try {
            Integer result = boardDao.updateBoardAndUserStatus(boardId, status.ordinal());
            return result == null ? -1 : result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
