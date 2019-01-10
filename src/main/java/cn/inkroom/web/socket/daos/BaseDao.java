package cn.inkroom.web.socket.daos;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 墨盒 on 16:43.
 * dao层基础类
 */
public class BaseDao {
    public static final String NAME_SPACE = "cn.inkroom.web.socket.sql";
    @Autowired
    private SqlSession session;

    protected SqlSession getSession() {
        return session;
    }

}
