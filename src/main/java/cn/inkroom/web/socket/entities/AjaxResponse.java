package cn.inkroom.web.socket.entities;


import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/**
 * ajax返回bean
 * Created by 墨盒 on 9:20.
 */

public class AjaxResponse {
    private int type;
    private boolean status;
    private String message;
    private JSONObject data;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setData(String data) {
//        ObjectMapper mapper = new ObjectMapper();
//      ObjectNode node =   mapper.createObjectNode();
//      mapper.getNodeFactory().
        this.data = JSONObject.fromObject(data);
    }

    public AjaxResponse(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public AjaxResponse() {
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
//        ObjectMapper mapper = new ObjectMapper();
//        JsonGenerator generator = null;
//        try {
////            generator = mapper.getFactory().createGenerator(System.out);
////            return generator.writeObject(this);
//            mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
//            mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
//            return mapper.writeValueAsString(this);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }

        return JSONSerializer.toJSON(this).toString();
    }

    public static void main(String[] args) {
        AjaxResponse ajax = new AjaxResponse();
        ajax.setStatus(true);
        ajax.setMessage("你好");
        ajax.setData("{\"chesses\":" +
                "[{\"x\":6,\"y\":2,\"id\":1}, " +
                "{\"x\":6,\"y\":6,\"id\":2}, " +
                "{\"x\":5,\"y\":5,\"id\":1}, " +
                "{\"x\":3,\"y\":7,\"id\":2}, " +
                "{\"x\": 3,\"y\": 3, \"id\": 1}, " +
                "{\"x\": 8, \"y\": 6, \"id\": \"2\"}, " +
                "{\"x\": 9, \"y\": 9, \"id\": \"1\"}]," +
                "\"userFlag\":2}");
        System.out.println(ajax.toString());
    }
}
