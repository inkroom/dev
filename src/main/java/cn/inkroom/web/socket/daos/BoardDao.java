package cn.inkroom.web.socket.daos;

import cn.inkroom.web.socket.entities.Board;
import cn.inkroom.web.socket.entities.User;
import cn.inkroom.web.socket.enums.BoardStatus;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 墨盒 on 14:37.
 * 棋局dao
 */
@Repository
public class BoardDao extends BaseDao {
    public List<Board> selectBoard(BoardStatus status) throws Exception {
        SqlSession session = getSession();
        List<Board> boards = session.selectList(NAME_SPACE + ".selectBoard", status.ordinal());
        session.clearCache();
        return boards;
    }

    public Board selectOneBoard(int boardId) throws Exception {
        SqlSession session = getSession();
        Board board = session.selectOne(NAME_SPACE + ".selectOneBoard", boardId);
        session.clearCache();
        return board;
    }

    /**
     * 创建棋局，默认创建者执白子
     *
     * @param userId 白方的id
     * @return 创建成功返回主键id，否则返回-1
     * @throws SQLException sql语句错误，抛出异常
     */
    public int createBoard(int userId) throws Exception {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("id", userId);
        SqlSession session = getSession();
        Integer result = session.selectOne(NAME_SPACE + ".insertBoard", map);
//        if (session.insert(NAME_SPACE + ".insertBoard", map) > -1) {
//            return map.get("boardId");
//        }
        session.clearCache();
        return result == null ? -1 : result;
    }

    /**
     * 更新棋局
     *
     * @param chess   刚下的棋子，json对象
     * @param boardId 棋局ID
     * @return 成功返回棋局ID，否则返回-1
     * @throws SQLException sql语句错误
     */
    public int updateBoard(String chess, int boardId) throws Exception {
        SqlSession session = getSession();
        HashMap<String, Object> map = new HashMap<String, Object>();
//        if (blackId != -1)//加入棋局
//            map.put("id", blackId);
        if (chess != null) {//落子
            map.put("chesses", chess);
        }
        map.put("boardId", boardId);
        int count = session.update(NAME_SPACE + ".updateBoard", map);
        if (count > 0) {
            return boardId;
        }
        session.clearCache();
        return -1;
    }

    /**
     * 加入棋局
     *
     * @param boardId 棋局id
     * @param userId  要加入棋局的用户
     * @return 加入的棋局
     */
    public Board joinBoard(int boardId, int userId) throws Exception {
        SqlSession session = getSession();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("boardId", boardId);
        map.put("userId", userId);
        Board board = session.selectOne(NAME_SPACE + ".updateUserAndBoard", map);
        session.clearCache();
        return board;
    }

    /**
     * 落子
     *
     * @param chess   棋子json数据
     * @param boardId 棋局id
     * @return 更新后的board对象
     */
    public Board insertChess(String chess, int boardId, int otherId) throws Exception {
        SqlSession session = getSession();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("chess", chess);
        map.put("boardId", boardId);
        map.put("otherUserId", otherId);

        Board board = session.selectOne(NAME_SPACE + ".updateBoard", map);
        session.clearCache();
        return board;
    }

    public Integer updateBoardAndUserStatus(int boardId, int boardStatus) {
        SqlSession session = getSession();
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("boardId", boardId);
        map.put("boardStatus", boardStatus);
        Integer result = session.selectOne(NAME_SPACE + ".updateBoardStatus", map);
        session.clearCache();
        return result;
    }
}
