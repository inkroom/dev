package cn.inkroom.web.excel.bean;

import java.util.List;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/11/24
 * @Time 10:34
 * @Descorption
 */
public class Question {
    private long id;
    private String title;
    private String answer;
    private String choice;
    private String difficult;
    private int type;
    private int subject;
    private int term;
    private int grade;
    private String analysis;
    private String resource;
    private List<String> resources;

    public List<String> getResources() {
        return resources;
    }

    public void setResources(List<String> resources) {
        this.resources = resources;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", answer='" + answer + '\'' +
                ", choice='" + choice + '\'' +
                ", difficult='" + difficult + '\'' +
                ", type=" + type +
                ", subject=" + subject +
                ", term=" + term +
                ", grade=" + grade +
                ", analysis='" + analysis + '\'' +
                ", resource='" + resource + '\'' +
                ", resources=" + resources +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public String getDifficult() {
        return difficult;
    }

    public void setDifficult(String difficult) {
        this.difficult = difficult;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSubject() {
        return subject;
    }

    public void setSubject(int subject) {
        this.subject = subject;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
}
