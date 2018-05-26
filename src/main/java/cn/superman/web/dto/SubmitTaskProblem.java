package cn.superman.web.dto;

public class SubmitTaskProblem {
    private String code;
    private String codeType;
    private Integer tid;
    private Integer problemId;
    private String cno;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    @Override
    public String toString() {
        return "SubmitTaskProblem{" +
                "code='" + code + '\'' +
                ", codeType='" + codeType + '\'' +
                ", tid=" + tid +
                ", problemId=" + problemId +
                ", cno='" + cno + '\'' +
                '}';
    }
}
