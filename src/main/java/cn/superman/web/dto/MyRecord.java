package cn.superman.web.dto;

public class MyRecord {
    private Long submitId;
    private String submitTime;
    private Long submitProblemId;
    private String problemName;
    private Long submitUserId;
    private String nickname;
    private Float score;
    private String isAccepted;
    private String details;
    private String codeLanguage;
    private String code;
    private Integer maxRuntime;
    private Integer maxMemory;

    public Long getSubmitId() {
        return submitId;
    }

    public void setSubmitId(Long submitId) {
        this.submitId = submitId;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public Long getSubmitProblemId() {
        return submitProblemId;
    }

    public void setSubmitProblemId(Long submitProblemId) {
        this.submitProblemId = submitProblemId;
    }

    public String getProblemName() {
        return problemName;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    public Long getSubmitUserId() {
        return submitUserId;
    }

    public void setSubmitUserId(Long submitUserId) {
        this.submitUserId = submitUserId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public String getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(String isAccepted) {
        this.isAccepted = isAccepted;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getCodeLanguage() {
        return codeLanguage;
    }

    public void setCodeLanguage(String codeLanguage) {
        this.codeLanguage = codeLanguage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getMaxRuntime() {
        return maxRuntime;
    }

    public void setMaxRuntime(Integer maxRuntime) {
        this.maxRuntime = maxRuntime;
    }

    public Integer getMaxMemory() {
        return maxMemory;
    }

    public void setMaxMemory(Integer maxMemory) {
        this.maxMemory = maxMemory;
    }

    @Override
    public String toString() {
        return "MyRecord{" +
                "submitId=" + submitId +
                ", submitTime='" + submitTime + '\'' +
                ", submitProblemId=" + submitProblemId +
                ", problemName='" + problemName + '\'' +
                ", submitUserId=" + submitUserId +
                ", nickname='" + nickname + '\'' +
                ", score=" + score +
                ", isAccepted='" + isAccepted + '\'' +
                ", details='" + details + '\'' +
                ", codeLanguage='" + codeLanguage + '\'' +
                ", code='" + code + '\'' +
                ", maxRuntime=" + maxRuntime +
                ", maxMemory=" + maxMemory +
                '}';
    }
}
