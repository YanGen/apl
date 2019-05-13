package com.muhuan.Service.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.muhuan.Service.mapper.ClassroomApplySheetMapper;
import com.muhuan.Service.mapper.UserMapper;
import com.muhuan.Service.pojo.flow.ClassroomApplySheet;
import com.muhuan.Service.pojo.flow.ProveClassroomParam;
import com.muhuan.Service.pojo.school.User;
import com.muhuan.Service.util.InnerRsaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author young
 * @ClassName: UserService
 * @Description: TODO()
 * @date 2019/3/3 23:25
 */
@Service
@CacheConfig(cacheNames="user")
public class UserService {
    @Autowired
    private ClassroomApplySheetMapper classroomApplySheetMapper;

    @Autowired
    private UserMapper userMapper;

    public List<User> getAll()
    {
        return userMapper.findAll();
    }

    @Cacheable(key = "#p0")
    public User get(Integer id)
    {
        return userMapper.get(id);
    }
    @CacheEvict(allEntries=true)
    public void insert(User user)
    {
        userMapper.save(user);
    }

    @CachePut(key = "#p0.sid")
    public User update(User student)
    {
        userMapper.update(student);
        return student;
    }

    @CacheEvict(allEntries=true)
    public void delete(Integer id)
    {
        userMapper.delete(id);
    }

    public User findUser(String username,String password,Integer power){
        return userMapper.findUser(username,password,power);
    }

    @Cacheable(key = "#p0-#p1")
    public PageInfo<User> getPage(int start, int size) {
        List<User> studentList = userMapper.findAll();
        PageHelper.startPage(start,size,"sid desc");
        PageInfo<User> pageInfo = new PageInfo<>(studentList);
        return pageInfo;
    }

    public boolean checkTime(Date start, Date end , Integer buildingId, Integer buildingNumber, String roomNumber){

        // 是否有交集
        int number = classroomApplySheetMapper.checkTime(start, end , buildingId, buildingNumber, roomNumber);
        System.out.println(number);

        if (number != 0)return false;

        return true;
    }

    public List<ClassroomApplySheet> missTime(Date start, Date end , Integer buildingId, Integer buildingNumber, String roomNumber){

        // 冲突表
        return classroomApplySheetMapper.missTimeSheets(start, end , buildingId, buildingNumber, roomNumber);

    }

    public void userApply(ClassroomApplySheet classroomApplySheet) {

        classroomApplySheetMapper.save(classroomApplySheet);

    }

    public Boolean tprove(ProveClassroomParam proveClassroomParam) {
        classroomApplySheetMapper.updateTProve(proveClassroomParam.getSheetId(),proveClassroomParam.getResult(),proveClassroomParam.getResult()?"等待管理员老师同意":"老师驳回",proveClassroomParam.getIllustrate());
        return true;
    }

    public List<ClassroomApplySheet> tprolst(String s) {

        return classroomApplySheetMapper.teacherProveList(s,new Date());

    }

    public List<ClassroomApplySheet> aprolst() {
        return classroomApplySheetMapper.adminProveList(new Date());
    }

    public Boolean aprove(ProveClassroomParam proveClassroomParam) {
        classroomApplySheetMapper.updateAProve(proveClassroomParam.getSheetId(),proveClassroomParam.getResult(),proveClassroomParam.getResult()?"本申请单通过":"管理员老师驳回",proveClassroomParam.getIllustrate());
        return true;
    }

    public void login(Integer id,Date d) {
        userMapper.updateLoginTime(id,d);
    }

    public List<User> tealst() {
        return userMapper.teacherList();
    }

    public String checkLoginTimeoutAndNewSession(User user,String innerPublicKey) throws Exception{
        long bt = new Date().getTime() - user.getLogin().getTime();
        if (bt/1000 > 60*10){
            return "timeout";
        }else {
            String userInfo = JSON.toJSONString(user);
            String token = InnerRsaUtil.publicEncrypt(userInfo, InnerRsaUtil.getPublicKey(innerPublicKey));
            return token;
        }
    }

    public List<ClassroomApplySheet> hislst(User user) {

        if (user.getPower() == 1) {
            return classroomApplySheetMapper.historyByP1(user.getCode());
        }
        if (user.getPower() == 2) {
            return classroomApplySheetMapper.historyByP2(user.getCode());
        }
        if (user.getPower() == 3) {
            return classroomApplySheetMapper.historyByP3(user.getCode());
        }

        return null;
    }
}
