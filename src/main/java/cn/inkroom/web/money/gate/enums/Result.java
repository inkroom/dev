package cn.inkroom.web.money.gate.enums;

public enum Result {
    SUCCESS(0),
    FAIL(1),//账号或密码错误
    LOGIN_NOT(2),//登陆失效或者未登录
    NO_AUTHORITY(3),//没有操作权限
    LOGIN_EXISTS(4),//已经异地登陆
    EXCEPTION(5),//发生异常
    PARAM_NOT_SUIT(6),
    PARAM_LENGTH_NOT_SUIT(7),//长度不合适
    PARAM_NOT_EMPTY(8),//不能为空
    FILE_SIZE_NOT_SUIT(9),//文件大小不正确
    FILE_TYPE_NOT_SUIT(10),//文件类型不正确
    CODE_NOT_EXISTS(11),//验证码不存在，或者已失效
    CODE_NOT_CORRECT(12)//验证码错误


    //额外的枚举类型，只能在这之后添加






    ;

    //额外的枚举类型，在这之前添加，这以下的勿动
    private int code;

    private Result(int code) {
        this.code = code;
    }

    public int value() {
        return this.code;
    }
}
