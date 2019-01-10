package cn.inkroom.web.socket.config;

/**
 * <p>常量</p>
 * <p>PAGE开头的为指示对应文件路径</p>
 * <p>URL开头为url，重定向使用</p>
 * <p>Created by 墨盒 on 16:24.</p>
 */
public class Contains {
    public static final String PAGE_ERROR = "error";
    public static final String PAGE_LOGIN = "login";
    public static final String PAGE_INDEX = "index";
    public static final String PAGE_FLAG = "flag";

    public static final String URL_LOGIN = "/login";
    public static final String URL_JOIN = "/{boardId:[1-9][0-9]*}/join";
    public static final String URL_INIT = "/init";
    public static final String URL_BOARD = "/board";
    public static final String URL_INDEX = "/index";
    public static final String URL_CREATE="/create";


    public static final String REDIRECT_INDEX = "redirect:" + URL_INDEX;
}
