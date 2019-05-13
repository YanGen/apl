package com.muhuan.Service.pojo.flow;

import java.util.Date;

public class ApplyClassroomParam {

    private String classroomArea1;
    private Integer classroomArea2;
    private Integer classroomArea3;
    private String classroomArea4;
    private String reason;
    private Date startTime;
    private Date endTime;
    private String applyPerson;
    private String applyPersonPhone;
    private String teacherCode;
    private String teacherName;


    public String getTeacherCode() {
        return teacherCode;
    }

    public void setTeacherCode(String teacherCode) {
        this.teacherCode = teacherCode;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getClassroomArea1() {
        return classroomArea1;
    }

    public void setClassroomArea1(String classroomArea1) {
        this.classroomArea1 = classroomArea1;
    }

    public Integer getClassroomArea2() {
        return classroomArea2;
    }

    public void setClassroomArea2(Integer classroomArea2) {
        this.classroomArea2 = classroomArea2;
    }

    public Integer getClassroomArea3() {
        return classroomArea3;
    }

    public void setClassroomArea3(Integer classroomArea3) {
        this.classroomArea3 = classroomArea3;
    }

    public String getClassroomArea4() {
        return classroomArea4;
    }

    public void setClassroomArea4(String classroomArea4) {
        this.classroomArea4 = classroomArea4;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getApplyPerson() {
        return applyPerson;
    }

    public void setApplyPerson(String applyPerson) {
        this.applyPerson = applyPerson;
    }

    public String getApplyPersonPhone() {
        return applyPersonPhone;
    }

    public void setApplyPersonPhone(String applyPersonPhone) {
        this.applyPersonPhone = applyPersonPhone;
    }
}
