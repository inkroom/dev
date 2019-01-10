package cn.inkroom.web.money.gate.dto.ctv;

import cn.inkroom.web.money.gate.enums.Result;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;

/**
 * 控制层交给视图层的dto对象
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MessageDto {

    private int status;
    private String message;
    private LinkedHashMap<String, Object> data = new LinkedHashMap<>();//封装json的map

    public MessageDto(Result status) {
        this.status = status.value();
    }

    public MessageDto() {
    }

    public MessageDto(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(Result status) {
        this.status = status.value();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void put(String key, String value) {
        data.put(key, value);
    }


    public Object get(String key){
        return data.get(key);
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
