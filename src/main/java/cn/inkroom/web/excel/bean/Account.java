package cn.inkroom.web.excel.bean;

import java.util.Date;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/11/30
 * @Time 15:55
 * @Descorption
 */
public class Account {

    private String stuId;
    private String name;
    private Date birthday;
    private String sex;
    private String current;
    private int group;
    private String attend;

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getAttend() {
        return attend;
    }

    public void setAttend(String attend) {
        this.attend = attend;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    @Override
    public String toString() {
        return "Account{" +
                "stuId='" + stuId + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", sex='" + sex + '\'' +
                ", current='" + current + '\'' +
                ", group=" + group +
                ", attend='" + attend + '\'' +
                '}';
    }
}
