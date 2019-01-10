package cn.inkroom.software.version.client.queue;

import cn.inkroom.software.version.common.app.Application;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * 用于存储app，
 */
public class ApplicationQueue {

    private Map<String, Application> map;

    public ApplicationQueue(List<Application> list) {
        map = new Hashtable<>();
        list.forEach(application -> map.put(application.getName(), application));
    }

    public Application getApplication(String app) {
        return map.get(app);
    }




}
