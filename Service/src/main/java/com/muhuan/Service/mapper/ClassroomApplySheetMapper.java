package com.muhuan.Service.mapper;

import com.muhuan.Service.pojo.flow.ClassroomApplySheet;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface ClassroomApplySheetMapper {

    @Insert("insert into flow_class_room_apply_sheet (user_code,building_number,apply_person,apply_person_phone,start,end,reason,teacher_code,teacher_name,state,building_id,has_prove,admin_has_prove,room_number) values (#{userCode},#{buildingNumber},#{applyPerson},#{applyPersonPhone},#{start},#{end},#{reason},#{teacherCode},#{teacherName},#{state},#{building},#{hasProve},#{adminHasProve},#{roomNumber}) ")
    public int save(ClassroomApplySheet classroomApplySheet);

    @Delete("delete from flow_class_room_apply_sheet where id= #{id} ")
    public void delete(int id);

    @Select("select * from flow_class_room_apply_sheet where id= #{id} ")
    @Results(id="sheetMap",value = {
            @Result(property = "userCode",column = "user_code"),
            @Result(property = "buildingNumber",column = "building_number"),
            @Result(property = "roomNumber",column = "room_number"),
            @Result(property = "applyPerson",column = "apply_person"),
            @Result(property = "applyPersonPhone",column = "apply_person_phone"),
            @Result(property = "teacherCode",column = "teacher_code"),
            @Result(property = "teacherName",column = "teacher_name"),
            @Result(property = "hasProve",column = "has_prove"),
            @Result(property = "adminHasProve",column = "admin_has_prove"),
            @Result(property = "building",column = "building_id"),
    })
    public ClassroomApplySheet get(int id);

    @Select("select count(*) from flow_class_room_apply_sheet where start < #{end} and end>#{start} and building_id = #{buildingId} and building_number = #{buildingNumber} and  room_number= #{roomNumber}")
    Integer checkTime(Date start, Date end, Integer buildingId, Integer buildingNumber, String roomNumber);

    @Select("select id,building_number,start,end,state,building_id,has_prove,admin_has_prove,room_number from flow_class_room_apply_sheet where start < #{end} and end>#{start} and building_id = #{buildingId} and building_number = #{buildingNumber} and  room_number= #{roomNumber}")
    @ResultMap(value = "sheetMap")
    List<ClassroomApplySheet> missTimeSheets(Date start, Date end, Integer buildingId, Integer buildingNumber, String roomNumber);

    @Update("update flow_class_room_apply_sheet set has_prove=#{b} , state=#{state} , illustrate=#{illustrate} where id=#{sheetId} ")
    void updateTProve(Integer sheetId, boolean b,String state,String illustrate);

    @Update("update flow_class_room_apply_sheet set admin_has_prove=#{b}, state=#{state}, illustrate=#{illustrate} where id=#{sheetId} ")
    void updateAProve(Integer sheetId, boolean b, String state, String illustrate);

    @Select("select * from flow_class_room_apply_sheet where has_prove = 0 and start > #{today}")
    @ResultMap(value = "sheetMap")
    List<ClassroomApplySheet> teacherProveList(String s,Date today);
    @Select("select * from flow_class_room_apply_sheet where ((teacher_code is not null and admin_has_prove = 0 and has_prove = 1) or (admin_has_prove = 0 and teacher_code is null)) and start > #{today}")
    @ResultMap(value = "sheetMap")
    List<ClassroomApplySheet> adminProveList(Date date);

    @Select("select * from flow_class_room_apply_sheet where user_code = #{code}")
    @ResultMap(value = "sheetMap")
    List<ClassroomApplySheet> historyByP1(String code);

    @Select("select * from flow_class_room_apply_sheet where user_code = #{code} or teacher_code = #{code}")
    @ResultMap(value = "sheetMap")
    List<ClassroomApplySheet> historyByP2(String code);

    @Select("select * from flow_class_room_apply_sheet")
    @ResultMap(value = "sheetMap")
    List<ClassroomApplySheet> historyByP3(String code);
}
