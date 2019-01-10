package cn.inkroom.web.socket.controllers;

import cn.inkroom.web.socket.annotation.InterceptorIgnore;
import cn.inkroom.web.socket.config.Contains;
import cn.inkroom.web.socket.config.KeyConfig;
import cn.inkroom.web.socket.config.MessageConfig;
import cn.inkroom.web.socket.entities.AjaxResponse;
import cn.inkroom.web.socket.entities.Board;
import cn.inkroom.web.socket.entities.User;
import cn.inkroom.web.socket.enums.UserStatus;
import cn.inkroom.web.socket.interceptors.LoginInterceptor;
import cn.inkroom.web.socket.services.BoardService;
import cn.inkroom.web.socket.services.UserService;
import cn.inkroom.web.socket.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 墨盒 on 21:20.
 */
@Controller
public class TestController {
    @Autowired
    private MessageConfig messageConfig;

    @Autowired
    private UserService userService;
    @Autowired
    private BoardService boardService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = Contains.URL_BOARD)
    public String toChess() {
        System.out.println(123123);
        return Contains.PAGE_FLAG;
    }

    @RequestMapping(value = Contains.URL_INDEX)
    public String toIndex() {
        request.setAttribute("boards", boardService.listBoard());
        return Contains.PAGE_INDEX;
    }

    @RequestMapping(value = Contains.URL_LOGIN, method = RequestMethod.GET)
    public String toLogin() {
        return Contains.PAGE_LOGIN;
    }

    @RequestMapping(value = Contains.URL_LOGIN, method = RequestMethod.POST)
    public String login(String account, String password) {
        System.out.println(account + "  " + password);
        User user = userService.login(account, password);
        System.out.println("user = " + user);
        if (user != null) {
            request.getSession().setAttribute(KeyConfig.KEY_LOGIN, user);
            return Contains.REDIRECT_INDEX;
        } else {
            return Contains.PAGE_ERROR;
        }
    }

    @RequestMapping(value = Contains.URL_JOIN)
    @ResponseBody
    public String join(@PathVariable int boardId) {
        User user = ((User) request.getSession().getAttribute(KeyConfig.KEY_LOGIN));
        Board board = boardService.createOrJoinBoard(boardId, user.getId());
        System.out.println("board =  " + board);
        if (board != null) {
            user.setBoardId(boardId);
        }
//        request.setAttribute();
        return new AjaxResponse((board != null), null).toString();
    }

    @RequestMapping(value = Contains.URL_INIT)
    @ResponseBody
    public String init() {
        User user = ((User) request.getSession().getAttribute(KeyConfig.KEY_LOGIN));
        Board board = boardService.getBoard(user.getBoardId());
        if (board == null) {
            return new AjaxResponse(false, null).toString();
        } else {
//            request.getSession().setAttribute("other", user.getStatus() == UserStatus.WHITE ? board.getBlack() : board.getWhite());
            return "{\"status\":true,\"chesses\":"
                    + JsonUtil.correctChess(board.getChesses(), user.getId()).toString()
//                    + board.getChesses().replaceAll("\"\\{", "{").replaceAll("\\}\"", "}")
                    + ",\"flag\":" + (user.getStatus() == UserStatus.WHITE) + ",\"boardStatus\":"
                    + board.getStatus().ordinal() + ",\"userFlag\":"
                    + user.getStatus().ordinal() + ",\"isMe\":" + (board.getNow() == user.getId()) + "}";
        }
    }

    //    @RequestMapping("/{x:[1-9][0-9]*]}/{y:[1-9][0-9]*]}/addChess")
//    public String addChess(@PathVariable int x, @PathVariable int y) {
//        Board board =
//        return JsonUtil.setStatus(true).toString();
//    }
    @RequestMapping(value = Contains.URL_CREATE)
    @ResponseBody
    public String create() {
        User user = (User) request.getSession().getAttribute(KeyConfig.KEY_LOGIN);
        Board board = boardService.createOrJoinBoard(user.getId(), -1);
        if (board != null) {
            user.setBoardId(board.getId());
            return new AjaxResponse(true, messageConfig.createBoard +
                    messageConfig.resultSuccess).toString();
        }
        return new AjaxResponse(false, messageConfig.createBoard +
                messageConfig.resultFail).toString();
    }

    @RequestMapping(value = "test")
    @ResponseBody
    @InterceptorIgnore(values = LoginInterceptor.class)
    public String test() {
        return new AjaxResponse(true, "测试中文").toString();
    }
}
