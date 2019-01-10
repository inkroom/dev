package cn.inkroom.software.version.common.app;

import java.util.Hashtable;
import java.util.Map;

public class AppList {
    private Map<String, Application> map;

    public AppList() {
        map = new Hashtable<>();
    }

    public Application getApp(String app) {
        return map.get(app);
    }

    public Application putApp(Application application) {
        return map.put(application.getName(), application);
    }

}
