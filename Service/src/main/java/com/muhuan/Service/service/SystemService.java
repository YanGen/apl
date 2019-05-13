package com.muhuan.Service.service;

import com.muhuan.Service.mapper.BuildingMapper;
import com.muhuan.Service.mapper.ClassroomApplySheetMapper;
import com.muhuan.Service.pojo.flow.ClassroomApplySheet;
import com.muhuan.Service.pojo.school.Building;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author young
 * @ClassName: SystemService
 * @Description: TODO()
 * @date 2019/4/23 16:50
 */
@Service
@CacheConfig(cacheNames = "system")
public class SystemService {

    @Autowired
    private ClassroomApplySheetMapper classroomApplySheetMapper;

    @Autowired
    private BuildingMapper buildingMapper;

    public List<Building> findAll(){
        return buildingMapper.findAll();
    }


}
