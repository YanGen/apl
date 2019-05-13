package com.muhuan.Service.pojo.school;

import java.util.List;
import java.util.Map;

/**
 * @author young
 * @ClassName: Building
 * @Description: TODO()
 * @date 2019/4/23 16:46
 */
public class Building {
    private Integer id;
    private String name;
    private Integer number;
    private String target;
    private String type;
    private List<Map<String,Object>> buildingItems;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Map<String, Object>> getBuildingItems() {
        return buildingItems;
    }

    public void setBuildingItems(List<Map<String, Object>> buildingItems) {
        this.buildingItems = buildingItems;
    }
}
