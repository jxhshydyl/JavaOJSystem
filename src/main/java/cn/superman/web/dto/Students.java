package cn.superman.web.dto;

import java.io.Serializable;

public class Students implements Serializable {
    private int id;
    private String sno;//学号
    private String password;//密码
    private String sname;//姓名
    private String ssex;//性别
    private String sbirthday;//出生年月
    private String email;//邮箱
    private String phone;//电话
    private String sage;//年龄
    private String syear;//入学年份
    private String sacademy;//所属学院
    private String smajor;//所属专业
    private String sclass;//所属班级
    private String taskName;//作业名称
    private String taskChapter;//作业章节
    private String sappendixes;//作业附件
    private String courseName;//课程名称
    private Float taskGrade;//作业成绩

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSsex() {
        return ssex;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex;
    }

    public String getSbirthday() {
        return sbirthday;
    }

    public void setSbirthday(String sbirthday) {
        this.sbirthday = sbirthday;
    }

    public String getSage() {
        return sage;
    }

    public void setSage(String sage) {
        this.sage = sage;
    }

    public String getSyear() {
        return syear;
    }

    public void setSyear(String syear) {
        this.syear = syear;
    }

    public String getSacademy() {
        return sacademy;
    }

    public void setSacademy(String sacademy) {
        this.sacademy = sacademy;
    }

    public String getSmajor() {
        return smajor;
    }

    public void setSmajor(String smajor) {
        this.smajor = smajor;
    }

    public String getSclass() {
        return sclass;
    }

    public void setSclass(String sclass) {
        this.sclass = sclass;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskChapter() {
        return taskChapter;
    }

    public void setTaskChapter(String taskChapter) {
        this.taskChapter = taskChapter;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Float getTaskGrade() {
        return taskGrade;
    }

    public void setTaskGrade(Float taskGrade) {
        this.taskGrade = taskGrade;
    }

    public String getSappendixes() {
        return sappendixes;
    }

    public void setSappendixes(String sappendixes) {
        this.sappendixes = sappendixes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Students{" +
                "id=" + id +
                ", sno='" + sno + '\'' +
                ", password='" + password + '\'' +
                ", sname='" + sname + '\'' +
                ", ssex='" + ssex + '\'' +
                ", sbirthday='" + sbirthday + '\'' +
                ", sage='" + sage + '\'' +
                ", syear='" + syear + '\'' +
                ", sacademy='" + sacademy + '\'' +
                ", smajor='" + smajor + '\'' +
                ", sclass='" + sclass + '\'' +
                ", taskName='" + taskName + '\'' +
                ", taskChapter='" + taskChapter + '\'' +
                ", courseName='" + courseName + '\'' +
                ", taskGrade=" + taskGrade +
                '}';
    }
}
