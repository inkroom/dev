package cn.inkroom.software.chat.common.util;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 墨盒 on 17:20.
 */
public class DefaultMessageReader implements MessageReader {
    private String value;

    @Override
    public String getSender() {
        Pattern pattern = Pattern.compile("<sender>(.*)</sender>");
        Matcher matcher = pattern.matcher(value);
        String result = null;
        if (matcher.find()) {
            result = matcher.group(1);
        }
        return result;
    }

    @Override
    public String getRecever() {
        return null;
    }

    @Override
    public String getMessage() {
        Pattern pattern = Pattern.compile("<msg>(.*)</msg>");
        Matcher matcher = pattern.matcher(value);
        String result = null;
        if (matcher.find()) {
            result = matcher.group(1);
        }
        return result;
    }

    @Override
    public Date getSendDate() {
        return null;
    }

    @Override
    public void init(String value) {
        this.value = value;
    }

    @Override
    public int getType() {

        Pattern pattern = Pattern.compile("<type>(.*)</type>");
        Matcher matcher = pattern.matcher(value);
        String result = null;
        if (matcher.find()) {
            result = matcher.group(1);
        }

        return Integer.parseInt(result);
    }
}
