package cn.inkroom.web.money.gate.utils.http;


import cn.inkroom.web.money.gate.config.BaseStatic;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/8/12
 * @Time 22:33
 * @Descorption
 */
public class ResponseUtil {
    public static void outJson(HttpServletResponse response, String value) throws Exception {
        if (!response.isCommitted()) {
            response.setContentType(BaseStatic.RESPONSE_CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.write(value);
            out.flush();
            out.close();
        }
    }

}
