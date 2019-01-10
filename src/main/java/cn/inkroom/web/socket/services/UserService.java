package cn.inkroom.web.socket.services;

import cn.inkroom.web.socket.daos.UserDao;
import cn.inkroom.web.socket.entities.Board;
import cn.inkroom.web.socket.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * Created by 墨盒 on 14:34.
 * 用户service
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User login(String account, String password) {
        User user = new User(account, password);
        try {
            return userDao.selectUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    public Board join(int boardId, int userId) {
//        try {
//            return userDao.joinBoard(boardId, userId);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
