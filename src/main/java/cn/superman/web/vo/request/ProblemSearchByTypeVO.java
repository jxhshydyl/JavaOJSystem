package cn.superman.web.vo.request;

import javax.validation.constraints.NotNull;

public class ProblemSearchByTypeVO {
    @NotNull(message = "请选择每页展示的条目数")
    private Integer pageShowCount;
    @NotNull(message = "请选择想查看的页数")
    private Integer wantPageNumber;
    @NotNull(message = "请选择具体的题目类型")
    private String problemTypeName;

    public Integer getPageShowCount() {
        return pageShowCount;
    }

    public void setPageShowCount(Integer pageShowCount) {
        this.pageShowCount = pageShowCount;
    }

    public Integer getWantPageNumber() {
        return wantPageNumber;
    }

    public void setWantPageNumber(Integer wantPageNumber) {
        this.wantPageNumber = wantPageNumber;
    }

    public String getProblemTypeName() {
        return problemTypeName;
    }

    public void setProblemTypeName(String problemTypeName) {
        this.problemTypeName = problemTypeName;
    }
}
