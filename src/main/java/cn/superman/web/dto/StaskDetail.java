package cn.superman.web.dto;

public class StaskDetail {
    private Long id;
    private Integer qid;
    private String sanswer;
    private Long usertime;
    private Integer usermemory;
    private Integer subcount;
    private String lastsubtime;
    private String score;
    private String Sno;
    private String Sname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQid() {
        return qid;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }

    public String getSanswer() {
        return sanswer;
    }

    public void setSanswer(String sanswer) {
        this.sanswer = sanswer;
    }

    public Long getUsertime() {
        return usertime;
    }

    public void setUsertime(Long usertime) {
        this.usertime = usertime;
    }

    public Integer getUsermemory() {
        return usermemory;
    }

    public void setUsermemory(Integer usermemory) {
        this.usermemory = usermemory;
    }

    public Integer getSubcount() {
        return subcount;
    }

    public void setSubcount(Integer subcount) {
        this.subcount = subcount;
    }

    public String getLastsubtime() {
        return lastsubtime;
    }

    public void setLastsubtime(String lastsubtime) {
        this.lastsubtime = lastsubtime;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getSno() {
        return Sno;
    }

    public void setSno(String sno) {
        Sno = sno;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    @Override
    public String toString() {
        return "StaskDetail{" +
                "id=" + id +
                ", qid=" + qid +
                ", sanswer='" + sanswer + '\'' +
                ", usertime=" + usertime +
                ", usermemory=" + usermemory +
                ", subcount=" + subcount +
                ", lastsubtime='" + lastsubtime + '\'' +
                ", score='" + score + '\'' +
                ", Sno='" + Sno + '\'' +
                ", Sname='" + Sname + '\'' +
                '}';
    }
}
