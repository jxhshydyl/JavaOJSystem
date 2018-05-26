package cn.superman.web.dto;

public class CourseDTO {
    private String sno;
    private String sname;
    private String classNo;
    private String className;
    private String courseNo;
    private String courseName;
    private String tid;
    private String taskName;
    private String count;

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "sno='" + sno + '\'' +
                ", sname='" + sname + '\'' +
                ", classNo='" + classNo + '\'' +
                ", className='" + className + '\'' +
                ", courseNo='" + courseNo + '\'' +
                ", courseName='" + courseName + '\'' +
                ", tid='" + tid + '\'' +
                ", taskName='" + taskName + '\'' +
                ", count='" + count + '\'' +
                '}';
    }
}
