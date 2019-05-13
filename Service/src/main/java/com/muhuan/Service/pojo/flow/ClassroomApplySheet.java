package com.muhuan.Service.pojo.flow;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class ClassroomApplySheet implements Serializable {
    private String Id;
    private String userCode;
    private Integer buildingNumber;
    private String roomNumber;
    private String applyPerson;
    private String applyPersonPhone;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date start;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date end;
    private String reason;
    private String teacherCode;
    private String teacherName;
    private Boolean hasProve = false;
    private Boolean adminHasProve = false;
    private String state;
    private String illustrate;
    private Integer building;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Integer getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(Integer buildingNumber) {
        this.buildingNumber = buildingNumber;
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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

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

    public Boolean getHasProve() {
        return hasProve;
    }

    public void setHasProve(Boolean hasProve) {
        this.hasProve = hasProve;
    }

    public Boolean getAdminHasProve() {
        return adminHasProve;
    }

    public void setAdminHasProve(Boolean adminHasProve) {
        this.adminHasProve = adminHasProve;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIllustrate() {
        return illustrate;
    }

    public void setIllustrate(String illustrate) {
        this.illustrate = illustrate;
    }

    public Integer getBuilding() {
        return building;
    }

    public void setBuilding(Integer building) {
        this.building = building;
    }

    @Override
    public String toString() {
        return "ClassroomApplySheet{" +
                "Id='" + Id + '\'' +
                ", userCode='" + userCode + '\'' +
                ", buildingNumber=" + buildingNumber +
                ", roomNumber='" + roomNumber + '\'' +
                ", applyPerson='" + applyPerson + '\'' +
                ", applyPersonPhone='" + applyPersonPhone + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", reason='" + reason + '\'' +
                ", teacherCode='" + teacherCode + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", hasProve=" + hasProve +
                ", adminHasProve=" + adminHasProve +
                ", state='" + state + '\'' +
                ", illustrate='" + illustrate + '\'' +
                ", building=" + building +
                '}';
    }
}
