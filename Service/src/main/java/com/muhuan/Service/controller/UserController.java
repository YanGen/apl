package com.muhuan.Service.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.muhuan.Service.pojo.flow.*;
import com.muhuan.Service.pojo.school.User;
import com.muhuan.Service.service.SystemService;
import com.muhuan.Service.service.UserService;
import com.muhuan.Service.util.InnerRsaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author young
 * @ClassName: UserController
 * @Description: TODO()
 * @date 2019/4/23 15:46
 */
@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    String innerPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCEXsYRKwxOCYsM3dwoF5gOXe2uXrEQsGBYQgBR_HJh0xjxnTEXeEIeSQlSJDprfSpBo8A3WbQoElbgfS6kejxJmZfB76RPdCzv3JhGFreFeXCpFGBeGUhlTvt008jGBsh_dbF6zrTum3HV6UMy-c3a2DtZvQY5oA19lhkog3LQtQIDAQAB";
    String innerPrivateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIRexhErDE4Jiwzd3CgXmA5d7a5esRCwYFhCAFH8cmHTGPGdMRd4Qh5JCVIkOmt9KkGjwDdZtCgSVuB9LqR6PEmZl8HvpE90LO_cmEYWt4V5cKkUYF4ZSGVO-3TTyMYGyH91sXrOtO6bcdXpQzL5zdrYO1m9BjmgDX2WGSiDctC1AgMBAAECgYA5myklhY94Uqzh75eFFyeEcRoj_W-fbLKxFiAfjfaVXhIH7DnWFe7oUS6uQbfu2WzPXh0juXUuzzRhw_jNeajMU_AHgPdE2ZCIG0Uf_JHSTYYsLRMJWe-BZYkerD-Vcn9x0jXRLhfZJm2oLa8M7wG0Uv1cZW2o3pWfM1J11ZSHAQJBAPY3pxYPMxizP9_ouX58lGiKsarzq44l-U6d924bPIZVs2aEJWQvq-S68iLmCjc3BLJd0RUyiSGB9GGRlQwvevUCQQCJoSYbDeZ8Xk4bdjonftFw5k9q8Iot6-9_RR5ZQxGDD7W9TX2Xv-N8jXza06cagsXFDgSRp_TaJenUpMp_E-bBAkBMp_8JSyopYoVcfORjkP3Tnnq6MqsS0pfP9jZ2vVwWps_39uR9UKo_yERiwSQlFKgNsE_MPbOcDw264sx4wr-RAkAf5SzDkFEd-Wz4iEmp5YX8OCOnkN81DTyPriD-ZsCkMvvXRyIYDOxXbSLGQ86yIiRbKoh8iQQBmk-6bAZRtPcBAkEApevY-6JdnyRPDHRPlAFrloeF0WW3g6nzgynrF9yU3gv6xWY-UFuwZx5nLdpBwcYDhzXDOWMcv6C3Kzrw5Pe7qw";

    String outsidePublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDcq2rzZLzbZpu2LUUMZUkxtsAC\n" +
            "iCfKK7RrdDTzIdniZEIpcPSBvAzfS6dtbAhefhw7ynHnm2Ee7YN6lzmBpIeBapvZ\n" +
            "OqRdaOxnCzOB+ZX3yyBqyHbJGWpA8gjDFmrq/ErQUMWSqgpf6vj3A3WqoWDEyUcS\n" +
            "oTqxyz7KzNZubJj7LQIDAQAB";
    String outsidePrivateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBANyravNkvNtmm7Yt\n" +
            "RQxlSTG2wAKIJ8ortGt0NPMh2eJkQilw9IG8DN9Lp21sCF5+HDvKceebYR7tg3qX\n" +
            "OYGkh4Fqm9k6pF1o7GcLM4H5lffLIGrIdskZakDyCMMWaur8StBQxZKqCl/q+PcD\n" +
            "daqhYMTJRxKhOrHLPsrM1m5smPstAgMBAAECgYB8G6C0MTUShFwREtbhyNlor2rA\n" +
            "Qcl3KCt5v8rD74b2kAKyAghSKuxmbctFfFwcoPrKGesEItx1o4mt2f2Kz3kxoaOP\n" +
            "RwYkHL3MQuIBV0/RzphVsO8OplzCcteR6lz8cB/Om7JkzquV2ZBfS9JUjHQKaNLu\n" +
            "PRnXyfhC6PUKn6vQIQJBAPhFTmpOki7JMBU8uHrB1ivjGiKUU8nCWaODA59LaBDw\n" +
            "I6McT/78hNsT+P8aapa7kXFbQSRnPwsk/riTAnwllrkCQQDjiiIWU+yynqs+H+4U\n" +
            "3mOLdUPcEwJnXVUNtT39aa68rfOZ5QPYwkzY6cynoZVhjIDzvti0Iar+1MZXOyqe\n" +
            "sY4VAkAs681eas0Ebh6nGQ+AFqZ71mGaNCBc9y9k6IW1Qt2XgvvPvYWz61jWkuyQ\n" +
            "q+TxVQrh6dMFlTDRAWadWuwuLlbxAkAGJhZzugLcdNM105EQeU4BV8LksJLRDkGd\n" +
            "JDevoGp7aMv7bafj9KQ0/GRuZzxtLWnSrGaYv4wqZL+TXeLx9ORdAkAK++uNMZKd\n" +
            "Pj9M/HXYKjjm+yaK5C19rJc130ZNIpM2EgTAzy5/jpSW2kJycbb2OCmIjQhzPUIx\n" +
            "sgh1hZQGvXn1";

    private RSAPublicKey rsaPublicKey;
    private RSAPrivateKey rsaPrivateKey;

    public UserController() {
        try {

            String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
            System.out.println(path);
            FileInputStream in = new FileInputStream("C:/keyPair");
            ObjectInputStream s = new ObjectInputStream(in);
            KeyPair keyPair = (KeyPair)s.readObject(); //恢复对象;
            this.rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
            this.rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
            System.out.println(rsaPrivateKey);
            System.out.println(rsaPublicKey);
            s.close();
            in.close();

        }catch (Exception e){
            System.out.println(e);
        }
    }


    @Autowired
    private SystemService systemService;
    @Autowired
    private UserService userService;

    private Map<String ,Object> dataMap = new HashMap<>();

    @RequestMapping(value = "/hislst")
    public Map<String ,Object> hislst(@RequestBody PostParam postParam) throws Exception{
        dataMap.put("success", true);
        dataMap.put("massage", "成功");

        String userInfo;
        try {
            userInfo = InnerRsaUtil.privateDecrypt(postParam.getToken(), InnerRsaUtil.getPrivateKey(innerPrivateKey));
        }catch (Exception e){
            dataMap.put("success",false);
            dataMap.put("massage","解析参数有误");
            return dataMap;
        }

        JSONObject jsonObject = JSONObject.parseObject(userInfo);
        User user = jsonObject.toJavaObject(jsonObject,User.class);

        String token = userService.checkLoginTimeoutAndNewSession(user,innerPublicKey);
        if (token.equals("timeout")){
            dataMap.put("success",false);
            dataMap.put("massage","请登录！");
            return dataMap;
        }
        dataMap.put("token",token);

        List<ClassroomApplySheet> results = userService.hislst(user);
        dataMap.put("results",results);
        return dataMap;
    }

    @RequestMapping(value = "/tealst",method = RequestMethod.POST)
    public Map<String,Object> tealst(@RequestBody PostParam postParam) throws Exception{
        dataMap.put("success", true);
        dataMap.put("massage", "成功");

        String userInfo;
        try {
            userInfo = InnerRsaUtil.privateDecrypt(postParam.getToken(), InnerRsaUtil.getPrivateKey(innerPrivateKey));
        }catch (Exception e){
            dataMap.put("success",false);
            dataMap.put("massage","解析参数有误");
            return dataMap;
        }

        JSONObject jsonObject = JSONObject.parseObject(userInfo);
        User user = jsonObject.toJavaObject(jsonObject,User.class);

        String token = userService.checkLoginTimeoutAndNewSession(user,innerPublicKey);
        if (token.equals("timeout")){
            dataMap.put("success",false);
            dataMap.put("massage","请登录！");
            return dataMap;
        }
        dataMap.put("token",token);

        List<User> result = userService.tealst();
        dataMap.put("results",result);

        return dataMap;
    }

    @RequestMapping(value = "/tprolst",method = RequestMethod.POST)
    public Map<String,Object> tprolst(@RequestBody PostParam postParam) throws Exception{
        dataMap.put("success", true);
        dataMap.put("massage", "成功");
        String userInfo;
        try {
            userInfo = InnerRsaUtil.privateDecrypt(postParam.getToken(), InnerRsaUtil.getPrivateKey(innerPrivateKey));
        }catch (Exception e){
            dataMap.put("success",false);
            dataMap.put("massage","解析参数有误");
            return dataMap;
        }

        JSONObject jsonObject = JSONObject.parseObject(userInfo);
        User user = jsonObject.toJavaObject(jsonObject,User.class);

        String token = userService.checkLoginTimeoutAndNewSession(user,innerPublicKey);
        if (token.equals("timeout")){
            dataMap.put("success",false);
            dataMap.put("massage","请登录！");
            return dataMap;
        }
        dataMap.put("token",token);

        if (user.getPower()!=2){
            dataMap.put("success",false);
            dataMap.put("massage","你没这权利。");
        }

        List<ClassroomApplySheet> results = userService.tprolst(user.getCode());
        dataMap.put("results",results);
        return dataMap;
    }

    @RequestMapping(value = "/tprove",method = RequestMethod.POST)
    public Map<String,Object> tprove(@RequestBody PostParam postParam) throws Exception{
        dataMap.put("success", true);
        dataMap.put("massage", "成功");

        String userInfo;
        try {
            userInfo = InnerRsaUtil.privateDecrypt(postParam.getToken(), InnerRsaUtil.getPrivateKey(innerPrivateKey));
        }catch (Exception e){
            dataMap.put("success",false);
            dataMap.put("massage","解析参数有误");
            return dataMap;
        }

        JSONObject jsonObject = JSONObject.parseObject(userInfo);
        User user = jsonObject.toJavaObject(jsonObject,User.class);

        String token = userService.checkLoginTimeoutAndNewSession(user,innerPublicKey);
        if (token.equals("timeout")){
            dataMap.put("success",false);
            dataMap.put("massage","请登录！");
            return dataMap;
        }
        dataMap.put("token",token);

        if (user.getPower()!=2){
            dataMap.put("success",false);
            dataMap.put("massage","你没这权利。");
        }

        postParam.setInfo(InnerRsaUtil.privateDecrypt(postParam.getInfo(), InnerRsaUtil.getPrivateKey(outsidePrivateKey)));

        jsonObject = JSONObject.parseObject(postParam.getInfo());
        ProveClassroomParam proveClassroomParam = jsonObject.toJavaObject(jsonObject, ProveClassroomParam.class);

        Boolean result = userService.tprove(proveClassroomParam);
        if (!result){
            dataMap.put("success",false);
            dataMap.put("massage","失败");
        }
        return dataMap;
    }

    @RequestMapping(value = "/aprolst",method = RequestMethod.POST)
    public Map<String,Object> aprolst(@RequestBody PostParam postParam) throws Exception{
        dataMap.put("success", true);
        dataMap.put("massage", "成功");
        String userInfo;
        try {
            userInfo = InnerRsaUtil.privateDecrypt(postParam.getToken(), InnerRsaUtil.getPrivateKey(innerPrivateKey));
        }catch (Exception e){
            dataMap.put("success",false);
            dataMap.put("massage","解析参数有误");
            return dataMap;
        }

        System.out.println(userInfo);
        JSONObject jsonObject = JSONObject.parseObject(userInfo);
        User user = jsonObject.toJavaObject(jsonObject,User.class);

        String token = userService.checkLoginTimeoutAndNewSession(user,innerPublicKey);
        if (token.equals("timeout")){
            dataMap.put("success",false);
            dataMap.put("massage","请登录！");
            return dataMap;
        }
        dataMap.put("token",token);
        if (user.getPower()!=3){
            dataMap.put("success",false);
            dataMap.put("massage","你没这权利。");
        }

        List<ClassroomApplySheet> results = userService.aprolst();
        dataMap.put("results",results);
        return dataMap;
    }

    @RequestMapping(value = "/aprove",method = RequestMethod.POST)
    public Map<String,Object> aprove(@RequestBody PostParam postParam) throws Exception{
        dataMap.put("success", true);
        dataMap.put("massage", "成功");

        String userInfo;
        try {
            userInfo = InnerRsaUtil.privateDecrypt(postParam.getToken(), InnerRsaUtil.getPrivateKey(innerPrivateKey));
        }catch (Exception e){
            dataMap.put("success",false);
            dataMap.put("massage","解析参数有误");
            return dataMap;
        }

        System.out.println(userInfo);

        JSONObject jsonObject = JSONObject.parseObject(userInfo);
        User user = jsonObject.toJavaObject(jsonObject,User.class);

        String token = userService.checkLoginTimeoutAndNewSession(user,innerPublicKey);
        if (token.equals("timeout")){
            dataMap.put("success",false);
            dataMap.put("massage","请登录！");
            return dataMap;
        }
        dataMap.put("token",token);

        if (user.getPower()!=3){
            dataMap.put("success",false);
            dataMap.put("massage","你没这权利。");
        }

        postParam.setInfo(InnerRsaUtil.privateDecrypt(postParam.getInfo(), InnerRsaUtil.getPrivateKey(outsidePrivateKey)));

        jsonObject = JSONObject.parseObject(postParam.getInfo());
        ProveClassroomParam proveClassroomParam = jsonObject.toJavaObject(jsonObject, ProveClassroomParam.class);

        Boolean result = userService.aprove(proveClassroomParam);
        if (!result){
            dataMap.put("success",false);
            dataMap.put("massage","失败");
        }
        return dataMap;
    }

    @RequestMapping(value = "/apply",method = RequestMethod.POST)
    public Map<String,Object> apply(@RequestBody PostParam postParam)throws Exception{
        dataMap.put("success",true);
        dataMap.put("massage","成功");
        String userInfo;
        try {
            userInfo = InnerRsaUtil.privateDecrypt(postParam.getToken(), InnerRsaUtil.getPrivateKey(innerPrivateKey));
        }catch (Exception e){
            dataMap.put("success",false);
            dataMap.put("massage","解析参数有误");
            return dataMap;
        }


        JSONObject jsonObject = JSONObject.parseObject(userInfo);
        User user = jsonObject.toJavaObject(jsonObject,User.class);

        String token = userService.checkLoginTimeoutAndNewSession(user,innerPublicKey);
        if (token.equals("timeout")){
            dataMap.put("success",false);
            dataMap.put("massage","请登录！");
            return dataMap;
        }
        dataMap.put("token",token);

//        byte[] de_result = RsaUtil.decrypt(rsaPrivateKey,RsaUtil.hexStringToBytes(postParam.getInfo()));
//        postParam.setInfo(new StringBuffer(new String(de_result)).reverse().toString());

        jsonObject = JSONObject.parseObject(postParam.getInfo());
        ApplyClassroomParam applyClassroomParam = jsonObject.toJavaObject(jsonObject,ApplyClassroomParam.class);

        Boolean result = userService.checkTime(applyClassroomParam.getStartTime(),applyClassroomParam.getEndTime(),applyClassroomParam.getClassroomArea2(),applyClassroomParam.getClassroomArea3(),applyClassroomParam.getClassroomArea4());
        if (result) {

            ClassroomApplySheet classroomApplySheet = new ClassroomApplySheet();
            classroomApplySheet.setStart(applyClassroomParam.getStartTime());
            classroomApplySheet.setEnd(applyClassroomParam.getEndTime());
            classroomApplySheet.setAdminHasProve(false);
            classroomApplySheet.setBuilding(applyClassroomParam.getClassroomArea2());
            classroomApplySheet.setBuildingNumber(applyClassroomParam.getClassroomArea3());
            classroomApplySheet.setApplyPerson(applyClassroomParam.getApplyPerson());
            classroomApplySheet.setApplyPersonPhone(applyClassroomParam.getApplyPersonPhone());
            classroomApplySheet.setReason(applyClassroomParam.getReason());
            classroomApplySheet.setRoomNumber(applyClassroomParam.getClassroomArea4());
            classroomApplySheet.setUserCode(user.getCode());
            classroomApplySheet.setState("等待指定老师批准。");
            classroomApplySheet.setTeacherCode(applyClassroomParam.getTeacherCode());
            classroomApplySheet.setTeacherName(applyClassroomParam.getTeacherName());
            userService.userApply(classroomApplySheet);

        }else {
            dataMap.put("success",false);
            dataMap.put("massage","时间冲突~");
        }
        return dataMap;
    }

    @RequestMapping(value = "/check",method = RequestMethod.POST)
    public Map<String,Object> check(@RequestBody PostParam postParam) throws Exception{
        dataMap.put("success",true);
        dataMap.put("massage","成功");

        String userInfo;
        try {
            userInfo = InnerRsaUtil.privateDecrypt(postParam.getToken(), InnerRsaUtil.getPrivateKey(innerPrivateKey));
        }catch (Exception e){
            dataMap.put("success",false);
            dataMap.put("massage","解析参数有误");
            return dataMap;
        }

        JSONObject jsonObject = JSONObject.parseObject(userInfo);
        User user = jsonObject.toJavaObject(jsonObject,User.class);

        String token = userService.checkLoginTimeoutAndNewSession(user,innerPublicKey);
        if (token.equals("timeout")){
            dataMap.put("success",false);
            dataMap.put("massage","请登录！");
            return dataMap;
        }
        dataMap.put("token",token);

//        byte[] de_result = RsaUtil.decrypt(rsaPrivateKey,RsaUtil.hexStringToBytes(postParam.getInfo()));
//        postParam.setInfo(new StringBuffer(new String(de_result)).reverse().toString());

        jsonObject = JSONObject.parseObject(postParam.getInfo());
        ApplyClassroomParam applyClassroomParam = jsonObject.toJavaObject(jsonObject,ApplyClassroomParam.class);
        Boolean results = userService.checkTime(applyClassroomParam.getStartTime(),applyClassroomParam.getEndTime(),applyClassroomParam.getClassroomArea2(),applyClassroomParam.getClassroomArea3(),applyClassroomParam.getClassroomArea4());
        dataMap.put("results",results);
        return dataMap;
    }

    @RequestMapping(value = "/miss",method = RequestMethod.POST)
    public Map<String,Object> miss(@RequestBody PostParam postParam) throws Exception{
        dataMap.put("success",true);
        dataMap.put("massage","成功");

        String userInfo;
        try {
            userInfo = InnerRsaUtil.privateDecrypt(postParam.getToken(), InnerRsaUtil.getPrivateKey(innerPrivateKey));
        }catch (Exception e){
            dataMap.put("success",false);
            dataMap.put("massage","解析参数有误");
            return dataMap;
        }

        JSONObject jsonObject = JSONObject.parseObject(userInfo);
        User user = jsonObject.toJavaObject(jsonObject,User.class);

        String token = userService.checkLoginTimeoutAndNewSession(user,innerPublicKey);
        if (token.equals("timeout")){
            dataMap.put("success",false);
            dataMap.put("massage","请登录！");
            return dataMap;
        }
        dataMap.put("token",token);

        postParam.setInfo(InnerRsaUtil.privateDecrypt(postParam.getInfo(), InnerRsaUtil.getPrivateKey(outsidePrivateKey)));

        System.out.println(postParam.getInfo());

        jsonObject = JSONObject.parseObject(postParam.getInfo());
        ApplyClassroomParam applyClassroomParam = jsonObject.toJavaObject(jsonObject,ApplyClassroomParam.class);

        List<ClassroomApplySheet> results = userService.missTime(applyClassroomParam.getStartTime(),applyClassroomParam.getEndTime(),applyClassroomParam.getClassroomArea2(),applyClassroomParam.getClassroomArea3(),applyClassroomParam.getClassroomArea4());
        dataMap.put("results",results);
        return dataMap;
    }



    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Map<String, Object> login(@RequestBody PostParam postParam) throws Exception{
        dataMap.put("success",true);
        dataMap.put("massage","成功!");

        postParam.setInfo(InnerRsaUtil.privateDecrypt(postParam.getInfo(), InnerRsaUtil.getPrivateKey(outsidePrivateKey)));

        JSONObject jsonObject = JSONObject.parseObject(postParam.getInfo());
        LoginParam loginParam = jsonObject.toJavaObject(jsonObject,LoginParam.class);

        User user = userService.findUser(loginParam.getUsername(),loginParam.getPassword(),loginParam.getPower());
        String userInfo;
        if (user != null){
            Date now = new Date();
            userService.login(user.getId(),now);
            user.setLogin(now);
            user.setPower(loginParam.getPower());
            userInfo = JSON.toJSONString(user);
        }else {
            dataMap.put("success",false);
            dataMap.put("massage","账号密码有误");
            return dataMap;
        }

        try {
            System.out.println(userInfo);
            String token = InnerRsaUtil.publicEncrypt(userInfo, InnerRsaUtil.getPublicKey(innerPublicKey));
            dataMap.put("token",token);
        }catch (Exception e){
            dataMap.put("success",false);
            dataMap.put("massage","系统错误，重试");
            dataMap.put("error",e);
        }
        return dataMap;
    }

}
