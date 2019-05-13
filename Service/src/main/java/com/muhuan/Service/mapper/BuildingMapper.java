package com.muhuan.Service.mapper;

import com.muhuan.Service.pojo.school.Building;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author young
 * @ClassName: BuildingMapper
 * @Description: TODO()
 * @date 2019/4/23 16:51
 */
@Mapper
public interface BuildingMapper {
    @Select("select * from school_building")
    List<Building> findAll();
}
