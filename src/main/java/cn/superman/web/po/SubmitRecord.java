package cn.superman.web.po;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class SubmitRecord implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3525247058399149957L;
	private BigInteger submitId;
	private String submitTime;
	private BigInteger submitProblemId;
	private String problemName;
	private Integer submitUserId;
	private Double score;
	private Integer isAccepted;
	private String details;
	private String codeLanguage;
	private String code;
	private String submitRecordTableName;
	private Integer isCompetition;//是否为比赛（非比赛为-1 比赛为比赛id）
	private String competitionPeoblemNumber;//问题序号  （非比赛为-1）
	private Integer submitCount;//提交次数
	private Integer acceptedTime;//通过时间

	public String getProblemName() {
		return problemName;
	}

	public void setProblemName(String problemName) {
		this.problemName = problemName;
	}

	public BigInteger getSubmitId() {
		return submitId;
	}

	public void setSubmitId(BigInteger submitId) {
		this.submitId = submitId;
	}

	public String getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}

	public BigInteger getSubmitProblemId() {
		return submitProblemId;
	}

	public void setSubmitProblemId(BigInteger submitProblemId) {
		this.submitProblemId = submitProblemId;
	}

	public Integer getSubmitUserId() {
		return submitUserId;
	}

	public void setSubmitUserId(Integer submitUserId) {
		this.submitUserId = submitUserId;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Integer getIsAccepted() {
		return isAccepted;
	}

	public void setIsAccepted(Integer isAccepted) {
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

	public String getSubmitRecordTableName() {
		return submitRecordTableName;
	}

	public void setSubmitRecordTableName(String submitRecordTableName) {
		this.submitRecordTableName = submitRecordTableName;
	}

	public Integer getAccepted() {
		return isAccepted;
	}

	public void setAccepted(Integer accepted) {
		isAccepted = accepted;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getIsCompetition() {
		return isCompetition;
	}

	public void setIsCompetition(Integer isCompetition) {
		this.isCompetition = isCompetition;
	}

	public String getCompetitionPeoblemNumber() {
		return competitionPeoblemNumber;
	}

	public void setCompetitionPeoblemNumber(String competitionPeoblemNumber) {
		this.competitionPeoblemNumber = competitionPeoblemNumber;
	}

	public Integer getSubmitCount() {
		return submitCount;
	}

	public void setSubmitCount(Integer submitCount) {
		this.submitCount = submitCount;
	}

	public Integer getAcceptedTime() {
		return acceptedTime;
	}

	public void setAcceptedTime(Integer acceptedTime) {
		this.acceptedTime = acceptedTime;
	}
}
