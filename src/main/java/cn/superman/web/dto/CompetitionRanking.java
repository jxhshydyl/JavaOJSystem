package cn.superman.web.dto;

import java.util.List;

public class CompetitionRanking {
    private Integer submitUserId;
    private String nickname;
    private String loginAccount;
    private Integer totalCount;
    private Integer totalTime;
    private Integer competitionProblemCount;
    private List<ProblemSubmitInfo> problemSubmitInfos;

    public Integer getSubmitUserId() {
        return submitUserId;
    }

    public void setSubmitUserId(Integer submitUserId) {
        this.submitUserId = submitUserId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<ProblemSubmitInfo> getProblemSubmitInfos() {
        return problemSubmitInfos;
    }

    public void setProblemSubmitInfos(List<ProblemSubmitInfo> problemSubmitInfos) {
        this.problemSubmitInfos = problemSubmitInfos;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    public Integer getCompetitionProblemCount() {
        return competitionProblemCount;
    }

    public void setCompetitionProblemCount(Integer competitionProblemCount) {
        this.competitionProblemCount = competitionProblemCount;
    }

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    @Override
    public String toString() {
        return "CompetitionRanking{" +
                "submitUserId=" + submitUserId +
                ", nickname='" + nickname + '\'' +
                ", totalCount=" + totalCount +
                ", totalTime=" + totalTime +
                ", competitionProblemCount=" + competitionProblemCount +
                ", problemSubmitInfos=" + problemSubmitInfos +
                '}';
    }
}
