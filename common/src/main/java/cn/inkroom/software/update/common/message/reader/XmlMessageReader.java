package cn.inkroom.software.update.common.message.reader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmlMessageReader implements MessageReader {


    private String message;
    private Pattern pattern;

    public XmlMessageReader() {
        pattern = Pattern.compile("<type>(.+)</type>");
    }

    @Override
    public int getPort() {
        return Integer.parseInt(getContent("port", getMessage()));
    }

    @Override
    public boolean readMessage(byte[] bytes) {
        message = new String(bytes);
        return true;
    }

    @Override
    public String getIp() {
        return getContent("ip", message);
    }

    @Override
    public String getMessage() {
        return message;
    }

    protected String getContent(String tag, String value) {
        Pattern pattern = Pattern.compile("<" + tag + ">(.+)" + "</" + tag + ">");
        Matcher matcher = pattern.matcher(value);
        if (matcher.find()) return matcher.group(1);
        return null;
    }

    @Override
    public String getApp() {
        return (getContent("app", message));
    }

    @Override
    public int getVersion() {
        return Integer.parseInt(getContent("version", message));
    }

    @Override
    public Type getType() {
        Matcher matcher = pattern.matcher(message);
        if (matcher.find())
            return Type.valueOf(matcher.group(1));
        return null;
    }
}
