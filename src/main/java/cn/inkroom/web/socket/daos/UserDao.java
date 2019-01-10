package cn.inkroom.web.socket.daos;

import cn.inkroom.web.socket.entities.Board;
import cn.inkroom.web.socket.entities.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by 墨盒 on 15:15.
 * 用户Dao
 */
@Repository
public class UserDao extends BaseDao {
    public User selectUser(User user) throws SQLException {
        SqlSession session = getSession();
        User newUser = session.selectOne(NAME_SPACE + ".selectUser", user);
        session.clearCache();
//        session.close();
        return newUser;
    }


}
