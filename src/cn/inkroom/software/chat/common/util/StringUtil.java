package cn.inkroom.software.chat.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 墨盒 on 20:17.
 */
public class StringUtil {
    public static String[] getImageString(String value) {
        java.util.regex.Pattern pattern = Pattern.compile("<img>.*</img>");
        Matcher matcher = pattern.matcher(value);
        ArrayList<String> strs = new ArrayList<>();
        while (matcher.find()) {
            strs.add(matcher.group());
        }
        String str[] = new String[strs.size()];
        return strs.toArray(str);
    }

    public static String[] getAllElement(String value) {
        String values[] = value.split("(</text> <img>|</img> <text>)");
        ArrayList<String> strs = new ArrayList<>();
        for (String temp : values) {
            temp = temp.trim();
            if (temp.startsWith("<img>") || temp.startsWith("<text>")) {
                temp = temp.substring(temp.indexOf(">") + 1);
            }
            if (temp.endsWith("</img>") || temp.endsWith("</text>")) {
                temp = temp.subSequence(0, temp.lastIndexOf("<")).toString();
            }
            strs.add(temp);
        }
        String str[] = new String[strs.size()];
        return strs.toArray(str);
    }

    public static List getContext(String html) {
        List resultList = new ArrayList();
        Pattern p = Pattern.compile(">([^</]+)</");//正则表达式 commend by danielinbiti
//        Pattern p = Pattern.compile("<(.*)>.*</\1>");
//        Pattern p = Pattern.compile("<.*>((?!<.*>).).*</.*>");
        Matcher m = p.matcher(html);//
        while (m.find()) {
            resultList.add(m.group(1));//
        }
        return resultList;
    }

}
