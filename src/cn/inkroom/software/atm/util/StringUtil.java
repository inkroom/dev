package cn.inkroom.software.atm.util;

/**
 * Created by 墨盒 on 15:36.
 */
public class StringUtil {
    public static Integer parseInt(String value){
        try {
            return Integer.parseInt(value);
        }catch (NumberFormatException e){
            return null;
        }
    }
    public static Float parseFloat(String value){
        try {
            return Float.parseFloat(value);
        }catch (NumberFormatException e){
            return null;
        }
    }
}
