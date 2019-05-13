package com.muhuan.Service.controller;

import com.muhuan.Service.pojo.school.Building;
import com.muhuan.Service.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author young
 * @ClassName: SystemController
 * @Description: TODO()
 * @date 2019/4/23 11:45
 */
@RestController
@RequestMapping("/system")
@CrossOrigin("*")
public class SystemController {
    private Map<String,Object> dataMap = new HashMap<>();
    @Autowired
    private SystemService systemService;


    @RequestMapping(value = "/build",method = RequestMethod.GET)
    public Map<String, Object> build(Model model){
        dataMap.put("success",true);
        dataMap.put("massage","成功!");

        List<Building> buildings = systemService.findAll();

        for (Building building : buildings){
            List<Map<String,Object>> buildingItems = new ArrayList<>();
            for (int i = 1 ;i<= building.getNumber();i++){
                Map<String,Object> map = new HashMap<>();
                map.put("id",i);
                map.put("buildingItem",building.getName()+i +"号");
                buildingItems.add(map);
            }
            building.setBuildingItems(buildingItems);
        }

        dataMap.put("result",buildings);

        return dataMap;
    }

}
