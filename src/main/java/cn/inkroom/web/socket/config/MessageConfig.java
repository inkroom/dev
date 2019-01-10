package cn.inkroom.web.socket.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 墨盒 on 15:11.
 */
@Configuration
public class MessageConfig {
    @Value("${event.create.board}")
    public String createBoard;
    @Value("${result.success}")
    public String resultSuccess;
    @Value("${result.fail}")
    public String resultFail;
}
