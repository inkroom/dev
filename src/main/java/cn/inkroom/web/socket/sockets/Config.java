package cn.inkroom.web.socket.sockets;

import cn.inkroom.web.socket.config.KeyConfig;
import cn.inkroom.web.socket.entities.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * Created by 墨盒 on 21:13.
 */
public class Config extends ServerEndpointConfig.Configurator {
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        super.modifyHandshake(sec, request, response);
        HttpSession httpSession = (HttpSession) request.getHttpSession();
        sec.getUserProperties().put(User.class.getName(), httpSession.getAttribute(KeyConfig.KEY_LOGIN));
//        sec.getUserProperties().put("other", httpSession.getAttribute("other"));//获取对手id
//        sec.getUserProperties().put(HttpSession.class.getName(), httpSession);
        sec.getUserProperties().put(ServletContext.class.getName(), ((HttpSession) request.getHttpSession()).getServletContext());
    }
}
