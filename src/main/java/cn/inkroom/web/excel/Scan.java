package cn.inkroom.web.excel;

import cn.inkroom.web.excel.bean.*;
import cn.inkroom.web.excel.dao.ExcelDao;
import cn.inkroom.web.excel.dao.Football;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.*;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/11/24
 * @Time 10:25
 * @Descorption
 */
@Component
public class Scan {
    @Autowired
    private ExcelDao excelDao;

    private ObjectMapper mapper = new ObjectMapper();


    private String getCellValue(Cell cell) {
        cell.setCellType(CellType.STRING);
        String value = cell.getStringCellValue();
        if (value == null)
            return null;
        return value.trim();
    }

    /**
     * 选择题
     *
     * @param row      行
     * @param question 问题bean
     * @param type     题目类型，1单选，2双选，3三选，4四选
     */
    private void scanChoice(Row row, Question question, int type) throws IOException {

        switch (type) {
            case 1://单选题
                //类型
                question.setType(1);
                question.setAnswer("[\"A\"]");
                break;
            case 2://双选题
                //类型
                question.setType(2);
                question.setAnswer("[\"A\",\"B\"]");
                break;
            case 3://三选题选题
                //类型
                question.setType(2);
                question.setAnswer("[\"A\",\"B\",\"C\"]");
                break;
            case 4://四选选题
                //类型
                question.setType(2);
                question.setAnswer("[\"A\",\"B\",\"C\",\"D\"]");
                break;
        }
        //拼接答案
        Map<String, String> map = new HashMap<String, String>();
        int j = 2;
        map.put("A", getCellValue(row.getCell(j++)));

        map.put("B", getCellValue(row.getCell(j++)));

        map.put("C", getCellValue(row.getCell(j++)));

        map.put("D", getCellValue(row.getCell(j++)));

        question.setChoice(mapper.writeValueAsString(map));

        //难度
        question.setDifficult(getCellValue(row.getCell(7)));
        //解析
        question.setAnalysis(getCellValue(row.getCell(8)));

    }

    /**
     * 判断题
     */
    private void scanJudge(Row row, Question question) throws IOException {
        //类型
        question.setType(3);
        question.setAnswer("[\"A\"]");
        //拼接答案
        Map<String, String> map = new HashMap<String, String>();
        map.put("A", getCellValue(row.getCell(2)));

        map.put("B", getCellValue(row.getCell(3)));
        question.setChoice(mapper.writeValueAsString(map));
        //难度
        question.setDifficult(getCellValue(row.getCell(5)));
        //解析
        question.setAnalysis(getCellValue(row.getCell(6)));
    }


    public Workbook getWorkBook(File file) {
        Workbook workbook = null;
        try {
            if (file.getName().endsWith(".xls"))
                workbook = new HSSFWorkbook(new FileInputStream(file));
            else
                workbook = new XSSFWorkbook(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return workbook;
    }

    public ArrayList<Coach> scanCoach(String path) {
        File file = new File(path);
        Workbook workbook = getWorkBook(file);
        Sheet sheet = workbook.getSheetAt(0);
        ArrayList<Coach> list = new ArrayList<>();
        for (int i = 2; i < sheet.getLastRowNum() + 1; i++) {
            Coach coach = new Coach();
            coach.setName(getCellValue(sheet.getRow(i).getCell(5)));
            coach.setOrg(getCellValue(sheet.getRow(i).getCell(6)));

            if (coach.getName() == null || coach.getName().equals(""))
                continue;
            list.add(coach);
        }
        return list;
    }

    @Transactional
    public void addCoach(ArrayList<Coach> list) {
        for (int i = 0; i < list.size(); i++) {
            //获取username；
//            boolean f = false;
            list.get(i).setUsername(xorInfo(list.get(i).getOrg()));
            if (football.selectCoach(list.get(i)) >= 1) {
                this.phone++;
//                f = true;
//                list.remove(i);
                list.get(i).setF(true);
                continue;
            }
//            if (f) {
//                list.get(i).setOther(list.get(i).getOrg());
//                list.get(i).setUsername(xorInfo(this.phone + ""));
//                list.get(i).setOrg(this.phone + "");
//            } else {
            list.get(i).setUsername(xorInfo(list.get(i).getOrg()));
//            }

            int count = football.insertAccountC(list.get(i));
            if (count < 1)
                throw new RuntimeException("a = " + count);
            count = football.insertCoach(list.get(i));
            if (count < 1)
                throw new RuntimeException("a = " + count);
            if (!list.get(i).isF())
                System.out.println(list.get(i));
        }
    }

    public List<Team> scanTeam(String path, String school) {
        File file = new File(path);
        Workbook workbook = getWorkBook(file);
        Sheet sheet = workbook.getSheetAt(0);
        LinkedList<Team> list = new LinkedList<>();
        for (int i = 2; i < sheet.getLastRowNum() + 1; i++) {
            Row row = sheet.getRow(i);
            Team team = new Team();
            if (getCellValue(row.getCell(1)) == null || getCellValue(row.getCell(1)).equals(""))
                continue;
            team.setName(school + getCellValue(row.getCell(1)));
            team.setOrg(getCellValue(row.getCell(2)));
            if (team.getName() == null || team.getName().equals(school) || team.getName().equals(school + "null"))
                continue;
            list.add(team);
        }
        return list;
    }

    @Transactional
    public void addTeam(List<Team> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Team team = list.get(i);
//            18161205016
//            System.out.println("4=" + team);
            if (team.getOrg() == null || team.getOrg().equals("")) {
                Long result = 0L;
//                result = football.selectUsername(xorInfo(String.valueOf(this.phone)));
//                System.out.println(result);
//                System.out.println("2="+team);
                while ((result = football.selectUsername(xorInfo(String.valueOf(this.phone)), 4)) != null) {
//                    System.out.println(this.phone + "重复了" + result);
                    this.phone++;
                }
                team.setOrg(this.phone + "");
//                System.out.println("1="+team);
                this.phone++;

//                System.out.println("3=" + team);
            }

//            System.out.println("2=" + team);
            while (football.selectUsername(xorInfo(team.getOrg()), 4) != null) {
                team.setOrg(String.valueOf(++this.phone));
            }

            team.setUsername(xorInfo(team.getOrg()));
//            System.out.println("1=" + team);
            int count = football.insertAccountT(team);
            if (count < 1)
                throw new RuntimeException("account = " + count);
            count = football.insertTeam(team);
            if (count < 1)
                throw new RuntimeException("account = " + count);
            System.out.println(team);
        }
    }


    public ArrayList<Player> scanPlayer(String path) {
        File file = new File(path);
        Workbook workbook = getWorkBook(file);
        Sheet sheet = workbook.getSheetAt(0);
        ArrayList<Player> list = new ArrayList<>();
        for (int i = 2; i < sheet.getLastRowNum() + 1; i++) {
            Player player = new Player();
            player.setName(getCellValue(sheet.getRow(i).getCell(3)));

            player.setSex(getCellValue(sheet.getRow(i).getCell(4)));
            if (player.getName() == null || player.getName().equals(""))
                continue;
//            System.out.println("第 "+ i+" 行"+" 第 1列="+getCellValue(sheet.getRow(i).getCell(3)));
            list.add(player);
        }
        return list;
    }

    public String xorInfo(String info) {
        byte[] infoBytes = info.getBytes();
        for (int i = 0; i < infoBytes.length; i++) {
            infoBytes[i] = (byte) (infoBytes[i] ^ 7);
        }

        return new String(infoBytes);
    }

    @Autowired
    private Football football;
    //18161205133L
    private long phone = 18761205129L;

    //
    @Transactional
    public void addPlayer(ArrayList<Player> list) {
        for (int i = 0; i < list.size(); i++) {
            //获取username；
            while (football.selectUsername(xorInfo(phone + ""), 1) != null) {
                this.phone++;
            }
            list.get(i).setUsername(xorInfo(this.phone + ""));
            list.get(i).setOrg(this.phone + "");

            int count = football.insertAccount(list.get(i));
            if (count < 1)
                throw new RuntimeException("a = " + count);
            count = football.insertPlayer(list.get(i));
            if (count < 1)
                throw new RuntimeException("a = " + count);
            System.out.println(list.get(i));
        }
    }


    /**
     * 扫描excel
     *
     * @param path 文件路径
     * @param type 1单选，2判断
     * @return
     */
    public ArrayList<Question> scanExcel(String path, int type, int grade, int term, int subject) {
        File file = new File(path);
        this.base = file.getParent();
        try {
            ArrayList<Question> array = new ArrayList<Question>();
            Workbook workbook = null;
            if (path.endsWith(".xls"))
                workbook = new HSSFWorkbook(new FileInputStream(file));
            else
                workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
                Row row = sheet.getRow(i);
                if (row == null || row.getCell(2) == null || row.getCell(2).getStringCellValue() == null || row.getCell(2).getStringCellValue().trim().equals(""))
                    break;
                Question question = new Question();
                int j = 0;
                System.out.println("第 " + i + " 行，第1列  " + row.getRowNum());
                //资源
                String resource = getCellValue(row.getCell(j++));
                if (resource != null && !(resource = resource.replaceAll(" 音频", ",音频")
                        .replace("，,", ",")
                        .replace(",,", ",")).equals("")) {
                    question.setResource(resource);
                    String resources[] = resource.split("[，,、]");

                    ArrayList<String> res = new ArrayList<>(4);
                    for (String r : res) {
                        if (r.equals(""))
                            res.remove(r);
                    }
                    Collections.addAll(res, resources);

                    if (res.size() == 2) {
                        res.sort((o1, o2) -> {
                            if (o1.contains("图片") && o2.contains("音频"))
                                return -1;
                            if (o1.contains("音频") && o2.contains("图片"))
                                return 1;
                            return 0;
                        });
                        System.out.println("排序后的结果");
                        question.setResources(res);
                    } else if (res.size() == 3) {
                        System.out.println("被跳过" + question);
                        continue;
                    }
                    question.setResources(res);
//                    ArrayList<String> temp = new ArrayList<String>(2);
//                    if (res.size()==2){
//                        for (String r :  res) {
//                            if ()
//                        }
//                    }
                }

                //题干
                question.setTitle(getCellValue(row.getCell(j++)));
                //答案，解析
                if (type < 5) {
                    scanChoice(row, question, type);
                } else {
                    scanJudge(row, question);
                }
                question.setSubject(subject);
                //年级
                question.setGrade(grade);
                //上下期
                question.setTerm(term);

                array.add(question);
            }
            return array;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String renameFile(String path, long id, int index) throws Exception {
        File file = new File(path);
        String suffix = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        File target = new File(file.getParent(), id + "-" + suffix + "-" + index + "." + suffix);
        System.out.println("原始文件路径=" + path + "   重命名路径= " + target.getAbsolutePath());
        System.out.println("新建结果=" + target.createNewFile());
        byte bytes[] = new byte[2 * 2048];
        FileOutputStream out = new FileOutputStream(target);
        FileInputStream in = new FileInputStream(file);
        int length;
        while ((length = in.read(bytes)) != -1) {
            out.write(bytes, 0, length);
        }
        out.flush();
        out.close();
        in.close();
        return target.getName();
    }

    private String base;

    @Transactional
    public void add(ArrayList<Question> qs) throws Exception {
        int i = 0;
        for (i = 0; i < qs.size(); i++) {
            try {
//                if (qs.get(i).getResource() != null && JsonMapper.getInstance().fromJson(qs.get(i).getResource(), ArrayList.class).size() == 3) {
//                    System.out.print("错误题目=" + qs.get(i).toString());
//                }
                System.out.println(qs.get(i));
                this.add(qs.get(i));
            } catch (Exception e) {
//                e.printStackTrace();
                throw e;
            }
        }
        System.out.println("共录入 " + i);
    }


    @Transactional
    public boolean add(Question questions) throws Exception {
        System.out.println("插入前的结果 = " + questions);
        int result = excelDao.insertQuestion(questions);
        if (result < 1) {
            throw new RuntimeException("插入失败" + result);
        }
        if (questions.getResources() != null && questions.getResources().size() > 0) {
            ArrayList<String> ss = new ArrayList<>();
            for (int i = 0; i < questions.getResources().size(); i++) {
                String resource = questions.getResources().get(i);
                if (resource.contains("图")) {
                    resource += ".png";
                } else if (resource.contains("音频")) {
                    resource += ".mp3";
                }
                try {
                    ss.add("asset/" + renameFile(base + File.separator + resource, questions.getId(), i + 1)
                            .replaceAll("\\\\", ""));
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException(e.getMessage());
                }
            }
            questions.setResource(mapper.writeValueAsString(ss));
        }
        System.out.println("插入的结果 = " + questions);
        result = excelDao.updateResource(questions);
        if (result < 1) {
            throw new RuntimeException("更新失败" + result);
        }
        return true;
    }


    public ArrayList<Account> scanAccount(String path, int start, int end, int group, String current) {
        File file = new File(path);
        ArrayList<Account> array = new ArrayList<Account>();
        Workbook workbook = null;
        try {
            if (path.endsWith(".xls"))
                workbook = new HSSFWorkbook(new FileInputStream(file));
            else
                workbook = new XSSFWorkbook(file);

            Sheet sheet = workbook.getSheetAt(0);
            for (int i = start; i < end; i++) {
                Row row = sheet.getRow(i);
                Account account = new Account();
                account.setGroup(group);
                account.setStuId(getCellValue(row.getCell(1)));
                account.setName(getCellValue(row.getCell(2)));
                account.setSex(getCellValue(row.getCell(3)));
//                Cell cell = row.getCell(4);
//                account.setBirthday(cell.getDateCellValue());
                account.setAttend("测试小学");
                account.setCurrent(current);

                array.add(account);
            }
            return array;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return null;
    }


}
