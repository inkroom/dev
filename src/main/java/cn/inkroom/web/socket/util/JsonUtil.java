package cn.inkroom.web.socket.util;

import cn.inkroom.web.socket.config.KeyConfig;
import cn.inkroom.web.socket.entities.Board;
import cn.inkroom.web.socket.enums.BoardStatus;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.awt.*;

/**
 * Created by 墨盒 on 21:17.
 */
public class JsonUtil {
    public static JSONObject setStatus(boolean result, JSONObject object) {
        object.put(KeyConfig.KEY_STATUS, result);
        return object;
    }

    public static JSONObject setStatus(boolean result) {
        return setStatus(result, new JSONObject());
    }

    public static Boolean[][] splitChess(String value, int id) {
        JSONArray array = JSONArray.fromObject(value);
        Boolean[][] chesses = new Boolean[Board.COLUMN_COUNT][Board.ROW_COUNT];
        for (int i = 0; i < array.size(); i++) {

            JSONObject temp = JSONObject.fromObject(array.getString(i));
            int x = temp.getInt(KeyConfig.KEY_X);
            int y = temp.getInt(KeyConfig.KEY_Y);
            boolean flag = temp.getInt(KeyConfig.KEY_ID) == id;
            chesses[x][y] = flag;
        }
        return chesses;
    }

    public static int getType(String value) {
        JSONObject object = JSONObject.fromObject(value);
        if (object != null)
            return object.getInt(KeyConfig.KEY_TYPE);
        return -1;
    }

    public static Point azaPoint(String value) {
        JSONObject object = JSONObject.fromObject(value);
        return new Point(object.getInt(KeyConfig.KEY_X), object.getInt(KeyConfig.KEY_Y));
    }

    public static Boolean[][] splitChess(String value) {
        JSONArray array = JSONArray.fromObject(value);
        Boolean[][] chesses = new Boolean[Board.COLUMN_COUNT][Board.ROW_COUNT];
        for (int i = 0; i < array.size(); i++) {

            System.out.println(array.get(i).toString());
            JSONObject object = JSONObject.fromObject(array.getString(i));
            int x = object.getInt(KeyConfig.KEY_X);
            int y = object.getInt(KeyConfig.KEY_Y);
            boolean flag = object.getBoolean(KeyConfig.KEY_FLAG);
            chesses[x][y] = flag;
        }
        return chesses;
    }

    public static JSONObject setBoardStatus(BoardStatus status) {
        JSONObject object = setStatus(true);
        object.put(KeyConfig.KEY_BOARD_STATUS, status.ordinal());
        object.put(KeyConfig.KEY_TYPE, KeyConfig.TYPE_STATUS);
        return object;
    }

    public static int getBoardStatus(String value) {
        JSONObject object = JSONObject.fromObject(value);
        return object.getInt(KeyConfig.KEY_BOARD_STATUS);
    }

    public static JSONObject spiltChess(String value, boolean isMe) {
        JSONObject object = JSONObject.fromObject(value);
        object = setStatus(true, object);
        object.put(KeyConfig.KEY_FLAG_USER, isMe);
        object.put(KeyConfig.KEY_TYPE, KeyConfig.TYPE_CHESS);
        return object;
    }

    public static JSONArray correctChess(String value, int id) {
        JSONArray array = JSONArray.fromObject(value);
        for (int i = 0; i < array.size(); i++) {
            JSONObject temp = JSONObject.fromObject(array.getString(i));
            temp.put(KeyConfig.KEY_FLAG, id == temp.getInt(KeyConfig.KEY_ID));
            array.set(i, temp);
        }
        return array;
    }
}
