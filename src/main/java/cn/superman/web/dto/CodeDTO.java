package cn.superman.web.dto;

import java.math.BigInteger;

public class CodeDTO {
    private BigInteger qid;//问题id
    private String qname;//问题名称
    private String qdescribe;//问题描述
    private String inputDescribe;//输入描述
    private String outputDescripe;//输出描述
    private String exampleInput;//输入示例
    private String exampleOutput;//输出示例
    private String referenceAnswer;//参考答案
    private String qtype;//问题类型
    private Integer limitTime;//限制时间
    private Integer limitMemory;//限制内存
    private Integer qdegree;//问题难度
    private Integer totalSubmitCount;//总提交次数
    private Integer totalRightCount;//正确提交次数
    private String qchapter;//所属章
    private String qparagraph;//所属节
    private String cname;//所属课程

    private Float score;//题目分数
    private String answer;//学生答案
    private Float grade;//学生得到的分数
    private String typeName;

    public BigInteger getQid() {
        return qid;
    }

    public void setQid(BigInteger qid) {
        this.qid = qid;
    }

    public String getQname() {
        return qname;
    }

    public void setQname(String qname) {
        this.qname = qname;
    }

    public String getQdescribe() {
        return qdescribe;
    }

    public void setQdescribe(String qdescribe) {
        this.qdescribe = qdescribe;
    }

    public String getInputDescribe() {
        return inputDescribe;
    }

    public void setInputDescribe(String inputDescribe) {
        this.inputDescribe = inputDescribe;
    }

    public String getOutputDescripe() {
        return outputDescripe;
    }

    public void setOutputDescripe(String outputDescripe) {
        this.outputDescripe = outputDescripe;
    }

    public String getExampleInput() {
        return exampleInput;
    }

    public void setExampleInput(String exampleInput) {
        this.exampleInput = exampleInput;
    }

    public String getExampleOutput() {
        return exampleOutput;
    }

    public void setExampleOutput(String exampleOutput) {
        this.exampleOutput = exampleOutput;
    }

    public String getReferenceAnswer() {
        return referenceAnswer;
    }

    public void setReferenceAnswer(String referenceAnswer) {
        this.referenceAnswer = referenceAnswer;
    }

    public String getQtype() {
        return qtype;
    }

    public void setQtype(String qtype) {
        this.qtype = qtype;
    }

    public Integer getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(Integer limitTime) {
        this.limitTime = limitTime;
    }

    public Integer getLimitMemory() {
        return limitMemory;
    }

    public void setLimitMemory(Integer limitMemory) {
        this.limitMemory = limitMemory;
    }

    public Integer getQdegree() {
        return qdegree;
    }

    public void setQdegree(Integer qdegree) {
        this.qdegree = qdegree;
    }

    public Integer getTotalSubmitCount() {
        return totalSubmitCount;
    }

    public void setTotalSubmitCount(Integer totalSubmitCount) {
        this.totalSubmitCount = totalSubmitCount;
    }

    public Integer getTotalRightCount() {
        return totalRightCount;
    }

    public void setTotalRightCount(Integer totalRightCount) {
        this.totalRightCount = totalRightCount;
    }

    public String getQchapter() {
        return qchapter;
    }

    public void setQchapter(String qchapter) {
        this.qchapter = qchapter;
    }

    public String getQparagraph() {
        return qparagraph;
    }

    public void setQparagraph(String qparagraph) {
        this.qparagraph = qparagraph;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "Code{" +
                "qid=" + qid +
                ", qname='" + qname + '\'' +
                ", qdescribe='" + qdescribe + '\'' +
                ", inputDescribe='" + inputDescribe + '\'' +
                ", outputDescripe='" + outputDescripe + '\'' +
                ", exampleInput='" + exampleInput + '\'' +
                ", exampleOutput='" + exampleOutput + '\'' +
                ", referenceAnswer='" + referenceAnswer + '\'' +
                ", qtype='" + qtype + '\'' +
                ", limitTime=" + limitTime +
                ", limitMemory=" + limitMemory +
                ", qdegree=" + qdegree +
                ", totalSubmitCount=" + totalSubmitCount +
                ", totalRightCount=" + totalRightCount +
                ", qchapter='" + qchapter + '\'' +
                ", qparagraph='" + qparagraph + '\'' +
                ", cname='" + cname + '\'' +
                '}';
    }
}
