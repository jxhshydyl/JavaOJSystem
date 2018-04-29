package cn.superman.web.vo.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import java.math.BigInteger;

public class CompetitionProblemAnswerVO {
	@NotBlank(message = "不能提交空代码")
	private String code;
	// 用于记录提交的代码语言，不过现在默认都是java
	@NotBlank(message = "请选择其中一个代码语言")
	private String codeType;
	@NotNull(message = "题目编号不能为空，请选中题目")
	private String problemId;

	private String competitionId;

	private String competitionPeoblemNumber;

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

	public String getProblemId() {
		return problemId;
	}

	public void setProblemId(String problemId) {
		this.problemId = problemId;
	}

	public String getCompetitionId() {
		return competitionId;
	}

	public void setCompetitionId(String competitionId) {
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
		return "CompetitionProblemAnswerVO{" +
				"code='" + code + '\'' +
				", codeType='" + codeType + '\'' +
				", problemId='" + problemId + '\'' +
				", competitionId='" + competitionId + '\'' +
				", competitionPeoblemNumber='" + competitionPeoblemNumber + '\'' +
				'}';
	}
}
