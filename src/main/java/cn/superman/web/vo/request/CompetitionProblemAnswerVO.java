package cn.superman.web.vo.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import java.math.BigInteger;

public class CompetitionProblemAnswerVO {
	@NotBlank(message = "不能提交空代码")
	private String code;
	// 用于记录提交的代码语言，不过现在默认都是java
	@NotBlank(message = "请选中其中一个代码语言")
	private String codeType;
	@NotNull(message = "题目编号不能为空，请选中题目")
	private Integer problemId;

	private BigInteger competitionId;

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

	public Integer getProblemId() {
		return problemId;
	}

	public void setProblemId(Integer problemId) {
		this.problemId = problemId;
	}

	public BigInteger getCompetitionId() {
		return competitionId;
	}

	public void setCompetitionId(BigInteger competitionId) {
		this.competitionId = competitionId;
	}

	@Override
	public String toString() {
		return "CompetitionProblemAnswerVO{" +
				"code='" + code + '\'' +
				", codeType='" + codeType + '\'' +
				", problemId=" + problemId +
				", competitionId=" + competitionId +
				'}';
	}
}
