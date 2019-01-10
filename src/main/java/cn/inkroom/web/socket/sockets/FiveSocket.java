package cn.inkroom.web.socket.sockets;

import cn.inkroom.web.socket.config.KeyConfig;
import cn.inkroom.web.socket.entities.Board;
import cn.inkroom.web.socket.entities.User;
import cn.inkroom.web.socket.enums.BoardStatus;
import cn.inkroom.web.socket.enums.UserStatus;
import cn.inkroom.web.socket.services.BoardService;
import cn.inkroom.web.socket.util.BoardUtil;
import cn.inkroom.web.socket.util.JsonUtil;
import net.sf.json.JSONObject;
import org.springframework.web.context.ContextLoader;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p style="color:red">测试</p>
 * <div style="width:10px;heigth:10px;background:green;"></div>
 * <ul><li>你</li><li>好</li></ul>
 * Created by 墨盒 on 19:39.
 */
@ServerEndpoint(value = "/flag", configurator = Config.class)
public class FiveSocket {

    //    @Resource(name = "boardService", type = BoardService.class)
    private BoardService service;

//    public void setService(BoardService service) {
//        this.service = service;
//    }

    private User user;
    private Integer other = -1;//对手的id

    //    private static final AtomicInteger connectionIds = new AtomicInteger(0);
    private static final List<FiveSocket> connections =
            new ArrayList<FiveSocket>();
    private static final BoardUtil board = new BoardUtil();

    private Session session;
//    public ChatAnnotation() {
//        nickname = GUEST_PREFIX + connectionIds.getAndIncrement();
//    }

    @OnOpen
    public void start(Session session, EndpointConfig config) {
        this.session = session;
        connections.add(this);
//        ServletContext servletContext = (ServletContext) config.getUserProperties().get(ServletContext.class.getName());
//        System.out.println("servletContext = " + servletContext);
//        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext((servletContext));
//        System.out.println("WebApplicationContext  " + webApplicationContext);

//        System.out.println("ContextLoader  " + ContextLoader.getCurrentWebApplicationContext());

        service = ContextLoader.getCurrentWebApplicationContext().getBean(BoardService.class);
//        String message = String.format("* %s %s", nickname, "has joined.");
//        httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
//        context = (ServletContext) config.getUserProperties().get(ServletContext.class.getName());
        System.out.println("service  " + service);
        user = (User) config.getUserProperties().get(User.class.getName());
        System.out.println(user.toString());
        Board board = service.getBoard(user.getBoardId());
        if (board.getBlack() == -1) {//尚未有另一方加入游戏
            other = -1;
        } else {//目前已有对手在线，
            other = user.getStatus() == UserStatus.WHITE ? board.getBlack() : board.getWhite();

            broadcastOne(JsonUtil.setBoardStatus(BoardStatus.CHESSING).toString(), other, true);//通知对方游戏开始
        }
        System.out.println(board.getStatus() + "   =====  " + board.getStatus().ordinal());
//        if (board.getPlayer() == 0) {
//            httpSession.setAttribute("flag", true);
//            flag = true;
//            board.setPlayer(1);
//            broadcast("请等待别人加入！", ONLY_ME);
//        } else {
//            flag = false;
//            httpSession.setAttribute("flag", false);
//            broadcast("游戏开始！", ALL);
//            board.setPlayer(2);
//        }
//        broadcast("有玩家加入！", BUT_ME);
        System.out.println("OnOpen");
    }


    @OnClose
    public void end() {
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        connections.remove(this);
//        String message = "对方已退出游戏！";
//        board.clear();
        broadcastOne("{\"message\":\"对方已退出游戏！\"}", other, false);
        System.out.println("OnClose");
    }

    /**
     * 收到消息
     *
     * @param message 收到的消息
     */
    @OnMessage
    public void incoming(String message) {
        System.out.println(user.getId() + " 收到的消息=" + message);
        switch (JsonUtil.getType(message)) {
            case KeyConfig.TYPE_CHESS:
                Point point = JsonUtil.azaPoint(message);
//        User user = (User) httpSession.getAttribute("user");
                Board result = service.insertChess(point.x, point.y, user.getId(), user.getBoardId(), other);
                if (result != null) {
                    System.out.println("发送给自己 " + user.getId() + "  的消息=" + JsonUtil.spiltChess(message, true).toString());
                    System.out.println("发送给对手 " + other + "  的消息=" + JsonUtil.spiltChess(message, false).toString());
                    broadcastOne(JsonUtil.spiltChess(message, true).toString(), user.getId(), false);//发送给自己
                    broadcastOne(JsonUtil.spiltChess(message, false).toString(), other, false);//发送给对手
                    board.init(result.getChesses(), user.getId());
                    //判断棋局输赢
                    boolean checkResult = board.check(user.getStatus() == UserStatus.WHITE, point.x, point.y);
                    if (checkResult) {//自己胜利
                        //更新棋局状态
                        if (service.updateBoardAndUserStatus(user.getBoardId(), user.getStatus() == UserStatus.WHITE ? BoardStatus.WHITE : BoardStatus.BLACK) != -1) {
                            //更新session存储的用户状态
//                            user.setBoardId(-1);
//                            user.setStatus(UserStatus.FREE);
                            broadcastOne(JsonUtil.setBoardStatus(user.getStatus() == UserStatus.BLACK ? BoardStatus.BLACK : BoardStatus.WHITE).toString(),
                                    user.getId(), true);//发送给自己，发送给服务端
                            broadcastOne(JsonUtil.setBoardStatus(user.getStatus() == UserStatus.BLACK ? BoardStatus.BLACK : BoardStatus.WHITE).toString(),
                                    other, true);//发送给对手，直接发送给服务端
                        } else {//更新棋局状态失败

                        }
                    }
                } else {
                    JSONObject object = JsonUtil.setStatus(false);
                    object.put(KeyConfig.KEY_TYPE, KeyConfig.TYPE_CHESS);
                    broadcastOne(object.toString(), user.getId(), false);//发送给自己
                }
                break;
            case KeyConfig.TYPE_STATUS:
                user.setStatus(UserStatus.FREE);
                user.setBoardId(-1);
                broadcastOne(message, user.getId(), false);
                break;

        }
        System.out.println("OnMessage");
    }


    @OnError
    public void onError(Throwable t) throws Throwable {
//        board.clear();
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        t.printStackTrace();
        connections.remove(this);
        System.out.println("OnError");
    }

    /**
     * @param message
     * @param userId
     * @param isServer 为true直接发送给服务器，否则发送给对应的客户端
     */
    private void broadcastOne(String message, int userId, boolean isServer) {
        for (FiveSocket client : connections) {
            synchronized (client) {
                if (client.user.getId() == userId) {
                    try {
                        if (isServer) {
                            client.incoming(message);
                        } else
                            client.session.getBasicRemote().sendText(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
    }
}
