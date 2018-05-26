package cn.superman.web.dto;

public class ProblemSubmitInfo {
    private Long submitProblemId;
    private String competitionPeoblemNumber;
    private Integer acceptedTime;
    private Integer submitCount;

    public Long getSubmitProblemId() {
        return submitProblemId;
    }

    public void setSubmitProblemId(Long submitProblemId) {
        this.submitProblemId = submitProblemId;
    }

    public Integer getAcceptedTime() {
        return acceptedTime;
    }

    public void setAcceptedTime(Integer acceptedTime) {
        this.acceptedTime = acceptedTime;
    }

    public Integer getSubmitCount() {
        return submitCount;
    }

    public void setSubmitCount(Integer submitCount) {
        this.submitCount = submitCount;
    }

    public String getCompetitionPeoblemNumber() {
        return competitionPeoblemNumber;
    }

    public void setCompetitionPeoblemNumber(String competitionPeoblemNumber) {
        this.competitionPeoblemNumber = competitionPeoblemNumber;
    }

    @Override
    public String toString() {
        return "ProblemSubmitInfo{" +
                "submitProblemId=" + submitProblemId +
                ", competitionPeoblemNumber='" + competitionPeoblemNumber + '\'' +
                ", acceptedTime=" + acceptedTime +
                ", submitCount=" + submitCount +
                '}';
    }
}
