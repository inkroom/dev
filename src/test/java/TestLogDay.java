import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 墨盒
 */
public class TestLogDay {

    private Logger logger = Logger.getLogger(getClass());

    @Test
    public void test() {

        logger.info("测试消息info");
        logger.debug("测试消息debug");
        logger.warn("测试消息");
        logger.error("测试消息");

    }

    @Test
    public void regex() {


        Pattern pattern = Pattern.compile("\\{.+\\}");
        Matcher matcher = pattern.matcher("/home/inkbox/log/log/{yyyy}/{MM}/{dd}_logSocket.log");
        while (matcher.find()) {
            System.out.println(matcher.group());
            System.out.println(matcher.group(1));
            System.out.println();
        }
    }
//    \{([^\{\}]+)\}
}
