package cn.superman.web.dto;

import java.io.Serializable;

public class TypeDTO implements Serializable {
    private String problemTypeId;
    private String problemTypeName;
    private String problemTypeDescription;
    private String counts;

    public String getProblemTypeId() {
        return problemTypeId;
    }

    public void setProblemTypeId(String problemTypeId) {
        this.problemTypeId = problemTypeId;
    }

    public String getProblemTypeName() {
        return problemTypeName;
    }

    public void setProblemTypeName(String problemTypeName) {
        this.problemTypeName = problemTypeName;
    }

    public String getProblemTypeDescription() {
        return problemTypeDescription;
    }

    public void setProblemTypeDescription(String problemTypeDescription) {
        this.problemTypeDescription = problemTypeDescription;
    }

    public String getCounts() {
        return counts;
    }

    public void setCounts(String counts) {
        this.counts = counts;
    }

    @Override
    public String toString() {
        return "TypeDTO{" +
                "problemTypeId='" + problemTypeId + '\'' +
                ", problemTypeName='" + problemTypeName + '\'' +
                ", problemTypeDescription='" + problemTypeDescription + '\'' +
                ", counts='" + counts + '\'' +
                '}';
    }
}
