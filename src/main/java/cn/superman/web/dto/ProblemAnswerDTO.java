package cn.superman.web.dto;

import java.math.BigInteger;

import cn.superman.web.po.User;

public class ProblemAnswerDTO {
	private String codeLanguage;
	private BigInteger submitProblemId;
	private User user;
	private String code;
	private Integer competitionId;
	private String competitionPeoblemNumber;

	public String getCodeLanguage() {
		return codeLanguage;
	}

	public void setCodeLanguage(String codeLanguage) {
		this.codeLanguage = codeLanguage;
	}

	public BigInteger getSubmitProblemId() {
		return submitProblemId;
	}

	public void setSubmitProblemId(BigInteger submitProblemId) {
		this.submitProblemId = submitProblemId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getCompetitionId() {
		return competitionId;
	}

	public void setCompetitionId(Integer competitionId) {
		this.competitionId = competitionId;
	}

	public String getCompetitionPeoblemNumber() {
		return competitionPeoblemNumber;
	}

	public void setCompetitionPeoblemNumber(String competitionPeoblemNumber) {
		this.competitionPeoblemNumber = competitionPeoblemNumber;
	}

	@Override
	public String toString() {
		return "ProblemAnswerDTO{" +
				"codeLanguage='" + codeLanguage + '\'' +
				", submitProblemId=" + submitProblemId +
				", user=" + user +
				", code='" + code + '\'' +
				", competitionId='" + competitionId + '\'' +
				", competitionPeoblemNumber='" + competitionPeoblemNumber + '\'' +
				'}';
	}
}
