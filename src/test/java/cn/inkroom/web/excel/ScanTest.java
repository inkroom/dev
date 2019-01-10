package cn.inkroom.web.excel;

import cn.inkroom.web.excel.bean.Account;
import cn.inkroom.web.excel.bean.Image;
import cn.inkroom.web.excel.bean.Images;
import cn.inkroom.web.excel.bean.Question;
import cn.inkroom.web.excel.dao.AccountDao;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.*;

import static org.junit.Assert.*;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/11/24
 * @Time 11:13
 * @Descorption
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:base.xml")
public class ScanTest {
    @Autowired
    AccountDao accountDao;

    @Autowired
    Scan scan;

    @Test
    public void scanBall() {

        scan.addTeam(scan.scanTeam("E:\\study\\项目组\\出差\\temp.xlsx","小超"));

//        scan.addPlayer(scan.scanPlayer("D:\\temp\\qq\\245900986\\FileRecv\\三台外国语学校球员信息.xlsx"));
//        scan.addPlayer(scan.scanPlayer("D:\\temp\\qq\\245900986\\FileRecv\\红星小学学校球员信息.xlsx"));
//        scan.addPlayer(scan.scanPlayer("F:\\广化小学学校球员信息.xlsx"));
//        scan.addPlayer(scan.scanPlayer("E:\\study\\项目组\\资料\\新建文件夹\\高一男队.xlsx"));
//        scan.addPlayer(scan.scanPlayer("E:\\study\\项目组\\出差\\三台中学校足球队球员信息.xlsx"));
//
//        scan.addPlayer(scan.scanPlayer("E:\\study\\项目组\\出差\\七一小学男子球员信息（足球）.xlsx"));
//        scan.addPlayer(scan.scanPlayer("E:\\study\\项目组\\出差\\七一小学校女足球员信息（足球）.xlsx"));
//        scan.addPlayer(scan.scanPlayer("E:\\study\\项目组\\出差\\三台一中学校球员信息.xlsx"));
//        录入教练
//        scan.addCoach(scan.scanCoach("E:\\study\\项目组\\出差\\红星小学学校球员信息.xlsx"));
//        scan.addCoach(scan.scanCoach("E:\\study\\项目组\\出差\\七一小学男子球员信息（足球）.xlsx"));
//        scan.addPlayer(scan.scanPlayer("E:\\study\\项目组\\出差\\七一小学校女足球员信息（足球）.xlsx"));
//        scan.addCoach(scan.scanCoach("E:\\study\\项目组\\出差\\三台外国语学校球员信息.xlsx"));
//        scan.addCoach(scan.scanCoach("E:\\study\\项目组\\出差\\三台一中学校球员信息.xlsx"));

//        scan.addCoach(scan.scanCoach("E:\\study\\项目组\\出差\\三台中学球员信息.xlsx"));
//        scan.addCoach(scan.scanCoach("E:\\study\\项目组\\出差\\temp.xlsx"));
//        scan.addCoach(scan.scanCoach("E:\\study\\项目组\\出差\\三台中学校足球队球员信息.xlsx"));
//        scan.addCoach(scan.scanCoach("E:\\study\\项目组\\出差\\原版\\广化小学学校球员信息.xlsx"));

//录入球队
//        scan.addTeam(scan.scanTeam("E:\\study\\项目组\\出差\\红星小学学校球员信息.xlsx","绵阳市三台县红星小学"));
//        scan.addTeam(scan.scanTeam("E:\\study\\项目组\\出差\\七一小学男子球员信息（足球）.xlsx","绵阳市三台县七一小学"));
//        scan.addTeam(scan.scanTeam("E:\\study\\项目组\\出差\\七一小学校女足球员信息（足球）.xlsx","绵阳市三台县七一小学"));
//        scan.addTeam(scan.scanTeam("E:\\study\\项目组\\出差\\三台外国语学校球员信息.xlsx","绵阳市三台县三台外国语学校"));
//        scan.addTeam(scan.scanTeam("E:\\study\\项目组\\出差\\三台一中学校球员信息.xlsx","绵阳市三台县三台一中学校"));
//        scan.addTeam(scan.scanTeam("E:\\study\\项目组\\出差\\三台一中学校球员信息.xlsx","绵阳市三台县三台一中学校"));
//        scan.addTeam(scan.scanTeam("E:\\study\\项目组\\出差\\原版\\广化小学学校球员信息.xlsx","绵阳市三台县广化东尚小学"));
//        scan.addTeam(scan.scanTeam("E:\\study\\项目组\\出差\\temp.xlsx","绵阳市三台县三台中学"));

    }

    @Test
    public void sort() {

        ArrayList<String> s1 = new ArrayList<String>();
        s1.add("A");
        s1.add("B");
        s1.add("C");
        s1.add("D");

        ArrayList<String> s2 = new ArrayList<String>();
        s2.add("1");
        s2.add("2");
        s2.add("3");
        s2.add("4");

        System.out.println(s1.subList(0, 1).get(0).hashCode() + "   " + s1.get(0).hashCode());
        Random r = new Random(10);


        Collections.shuffle(s1, r);
        Collections.shuffle(s2, r);

        System.out.println(s1);
        System.out.println(s2);

    }


    //                        "音乐\\10-28八年级上册-音乐-原\\10-28八年级上册\\八年级上册\\八上多选题（双选）\\" +
//                        "音乐\\10-28八年级上册-音乐-原\\10-28八年级上册\\八年级上册\\八上多选题（三选）\\" +
//                        "音乐\\10-28八年级上册-音乐-原\\10-28八年级上册\\八年级上册\\八上多选题（四选）\\" +
//                        "音乐\\10-28八年级上册-音乐-原\\10-28八年级上册\\八年级上册\\判断题\\" +
    //                "八上多选题（双选选）2.xls"
//                "八上多选题（三选）2.xls"
//                "八上多选题（四选）2.xls"
//                "八上判断题2.xls"
    @Test
    public void test() {
        ArrayList<Question> qs = scan.scanExcel("E:\\study\\项目组\\exam_system\\题库\\" +
                "音乐\\12-8八年级上册\\八年级上册\\八年级上册\\八上判断题\\" +
                "八上判断题.xls", 5, 8, 1, 2);
        try {
            scan.add(qs);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        int i = 0;
//        for (i = 0; i < qs.size(); i++) {
//            try {
////                if (qs.get(i).getResource() != null && JsonMapper.getInstance().fromJson(qs.get(i).getResource(), ArrayList.class).size() == 3) {
////                    System.out.print("错误题目=" + qs.get(i).toString());
////                }
//                System.out.println(qs.get(i));
//                scan.add(qs.get(i));
//            } catch (Exception e) {
//                e.printStackTrace();
//                throw e;
//            }
//        }
//        System.out.println("共录入 " + i);
    }

    @Test
    public void insertAccount() {
        ArrayList<Account> array = scan.scanAccount("E:\\study\\项目组\\exam_system\\音乐上机测试学生名单-东辰初二21班学生学籍.xls",
                2, 58, 7, "东辰");

        int i = 0;
        Date now = new Date();
        for (; i < array.size(); i++) {
            Account account = array.get(i);
            System.out.println(account);
//            accountDao.insert(account.getStuId(), account.getName(), account.getBirthday(),
//                    account.getSex(), account.getAttend(), account.getCurrent(),
//                    account.getGroup(), now);
            accountDao.insertSec(account.getStuId(), account.getName(),
                    account.getSex(), account.getAttend(), account.getCurrent(),
                    account.getGroup(), now);
        }
        System.out.println("共录入" + i);
    }

    @Test
    public void download() {

        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("D:\\2.txt"));
            String line;
            while ((line = reader.readLine()) != null)
                builder.append(line);
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(builder.toString());
        JsonMapper mapper = JsonMapper.getInstance();
        Images images = mapper.fromJson(builder.toString(), Images.class);
        System.out.println(images);


    }

}