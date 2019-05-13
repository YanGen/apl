package com.muhuan.Service.mapper;

import com.muhuan.Service.pojo.school.User;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * @author young
 * @ClassName: UserMapper
 * @Description: TODO()
 * @date 2019/3/2 14:53
 */
@Mapper
public interface UserMapper {
    @Select("select * from school_user ")
    List<User> findAll();


    @Insert("insert into school_user ( name ) values (#{name}) ")
    public int save(User user);
    @Delete("delete from school_user where id= #{id} ")
    public void delete(int id);

    @Select("select * from school_user where id= #{id} ")
    public User get(int id);

    @Select("select code,name,phone,power,sex,unit,grade from school_user where code=#{username} and password = #{password} and power = #{power} ")
    public User findUser(String username,String password,Integer power);

    @Update("update school_user set name=#{name} where id=#{id} ")
    public int update(User student);

    @Update("update school_user set login = #{d} where id=#{id} ")
    void updateLoginTime(Integer id, Date d);

    @Select("select code,name,sex,unit,grade from school_user where  power = 2 ")
    List<User> teacherList();
}
