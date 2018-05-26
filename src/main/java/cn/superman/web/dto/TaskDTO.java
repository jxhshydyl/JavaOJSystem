package cn.superman.web.dto;

import org.springframework.web.multipart.MultipartFile;

public class TaskDTO {
    private int tid;//任务id
    private String taskName;//任务名称
    private String tappendixes;//任务附件
    private float tscore;//任务分数
    private String tno;//教师编号
    private String tname;//教师名称
    private String subtime;//创建时间
    private String ttype;//作业类型
    private String tstate;//作业状态
    private Integer taskDiff;//任务难度
    private String cno;//课程编号
    private String cname;//课程名称
    private String tchapter;//所属章节
    private String isAnswer;//是否开启答案（是，否）
    private String isMessage;//是否开启消息(是，否)
    private String isEqully;//学生题目是否相同(true,false)
    private MultipartFile appendixesFile;
    private String startTime;
    private String endTime;

    public MultipartFile getAppendixesFile() {
        return appendixesFile;
    }

    public void setAppendixesFile(MultipartFile appendixesFile) {
        this.appendixesFile = appendixesFile;
    }

    public String getIsAnswer() {
        return isAnswer;
    }

    public void setIsAnswer(String isAnswer) {
        this.isAnswer = isAnswer;
    }

    public String getIsMessage() {
        return isMessage;
    }

    public void setIsMessage(String isMessage) {
        this.isMessage = isMessage;
    }

    public String getIsEqully() {
        return isEqully;
    }

    public void setIsEqully(String isEqully) {
        this.isEqully = isEqully;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTappendixes() {
        return tappendixes;
    }

    public void setTappendixes(String tappendixes) {
        this.tappendixes = tappendixes;
    }

    public float getTscore() {
        return tscore;
    }

    public void setTscore(float tscore) {
        this.tscore = tscore;
    }

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getSubtime() {
        return subtime;
    }

    public void setSubtime(String subtime) {
        this.subtime = subtime;
    }

    public String getTtype() {
        return ttype;
    }

    public void setTtype(String ttype) {
        this.ttype = ttype;
    }

    public String getTstate() {
        return tstate;
    }

    public void setTstate(String tstate) {
        this.tstate = tstate;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getTchapter() {
        return tchapter;
    }

    public void setTchapter(String tchapter) {
        this.tchapter = tchapter;
    }

    public Integer getTaskDiff() {
        return taskDiff;
    }

    public void setTaskDiff(Integer taskDiff) {
        this.taskDiff = taskDiff;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Task{" +
                "tid=" + tid +
                ", taskName='" + taskName + '\'' +
                ", tappendixes='" + tappendixes + '\'' +
                ", tscore=" + tscore +
                ", tno='" + tno + '\'' +
                ", tname='" + tname + '\'' +
                ", subtime='" + subtime + '\'' +
                ", ttype='" + ttype + '\'' +
                ", tstate='" + tstate + '\'' +
                ", cno='" + cno + '\'' +
                ", cname='" + cname + '\'' +
                ", tchapter='" + tchapter + '\'' +
                ", isAnswer='" + isAnswer + '\'' +
                ", isMessage='" + isMessage + '\'' +
                ", isEqully='" + isEqully + '\'' +
                ", appendixesFile=" + appendixesFile +
                '}';
    }
}
