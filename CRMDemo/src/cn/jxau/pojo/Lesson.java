package cn.jxau.pojo;

public class Lesson {
    private Integer id;
    private String lessonName;
    private double lessonCost;
    private short totalTime;
    private String remark;

    public Lesson() {

    }

    public Lesson(Integer id, String lessonName, double lessonCost, short totalTime, String remark) {
        this.id = id;
        this.lessonName = lessonName;
        this.lessonCost = lessonCost;
        this.totalTime = totalTime;
        this.remark = remark;
    }
    public Lesson( String lessonName, double lessonCost, short totalTime, String remark) {
        this.lessonName = lessonName;
        this.lessonCost = lessonCost;
        this.totalTime = totalTime;
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public double getLessonCost() {
        return lessonCost;
    }

    public void setLessonCost(double lessonCost) {
        this.lessonCost = lessonCost;
    }

    public short getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(short totalTime) {
        this.totalTime = totalTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", lessonName='" + lessonName + '\'' +
                ", lessonCost=" + lessonCost +
                ", totalTime=" + totalTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}