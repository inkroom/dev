package cn.inkroom.web.teach.bean;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/11/29
 * @Time 15:04
 * @Descorption
 */
public class Person {

    @Pattern(regexp = "[AB]", message = "名字只能是AB")
    private String name;


    @DecimalMin(value = "10", message = "最小值为10")
    @DecimalMax(value = "100", message = "最大值为100")
    private Integer age;
    @Past(message = "必须是过去的时间")
    @DateTimeFormat(pattern = "yyyy")
    private Date birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }


    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }
}
