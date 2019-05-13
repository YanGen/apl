# apl
一套申请课室的接口服务
公共接口：

登录
路径：/user/login
方法：post
头部（不包括通用参数）：
①asin（签名验证）：md5(info=user|password|power &_=timestamp)
类型：String

参数：
必须：
①info:  rsa(user|password|power )
类型：String
解释：一个拼凑而来的字符串，user账号ps密码power权限，权限值学生1老师2管理3，在RSA公钥加密后得到的字符串 
加密前例子：”1620...|123456|1”
②_：timestamp
类型：String
解释：一个毫秒级时间戳
可选：
无
返回：
正确：
一个有效时长为1小时的token
错误：
异常消息（界面提示）

获取楼宇信息
路径：/system/build
方法：get
返回：
正确：
全校所有楼宇信息
错误：
异常消息（界面提示）


查询时间冲突（和申请提交的参数一毛一样）：
路径：/user/check
方法：post
头部（不包括通用参数）：
①asin（签名验证）：md5(info=user|password|power &_=timestamp)
类型：String

参数：
必须：
①info:  rsa(一个申请信息的json)
类型：String
解释：json的键值如下

private String classroomArea1;
    private String classroomArea2;
    private String classroomArea3;
    private String classroomArea4;
    private String reason;
    private Date startTime;
    private Date endTime;
    private String applyPerson;
    private String applyPersonPhone;

加密前例子：”1620...|123456|1”
②_：timestamp
类型：String
解释：一个毫秒级时间戳
可选：
无
返回：
正确：
返回成功（result的值就是是否冲突情况true即冲突，false 不冲突）
错误：
异常消息（界面提示）



用户接口：

申请
路径：/user/apply
方法：post
头部（不包括通用参数）：
①asin（签名验证）：md5(info=一串参数json &_=timestamp)
类型：String

参数：
必须：
①info:  rsa(一个申请信息的json)
类型：String
解释：json的键值如下

private String classroomArea1;
    private String classroomArea2;
    private String classroomArea3;
    private String classroomArea4;
    private String reason;
    private Date startTime;
    private Date endTime;
    private String applyPerson;
    private String applyPersonPhone;

加密前例子：
"{"classroomArea1":"教学区域","classroomArea2":1,"classroomArea3":3,"classroomArea4":"111","reason":"理由","startTime":"2019-04-25 20:45:30","endTime":"2019-04-25 21:50:30","applyPerson":"陈美琪","applyPersonPhone":"1654648646"}"
②_：timestamp
类型：String
解释：一个毫秒级时间戳
③token:登录操作时返回的一个令牌
可选：
无
返回：
正确：
返回成功（此时应该跳转消息页面，也就是申请情况）
错误：
异常消息（界面提示）


查询时间冲突的申请表（和申请提交的参数一毛一样）：
路径：/user/miss
方法：post
头部（不包括通用参数）：
①asin（签名验证）：md5(info=user|password|power &_=timestamp)
类型：String

参数：
必须：
①info:  rsa(一个申请信息的json)
类型：String
解释：json的键值如下

private String classroomArea1;
    private String classroomArea2;
    private String classroomArea3;
    private String classroomArea4;
    private String reason;
    private Date startTime;
    private Date endTime;
    private String applyPerson;
    private String applyPersonPhone;

加密前例子：”1620...|123456|1”
②_：timestamp
类型：String
解释：一个毫秒级时间戳
③token:登录操作时返回的一个令牌
可选：
无
返回：
正确：
返回成功（result的就是冲突的申请表，隐私信息已经过后台屏蔽）
错误：
异常消息（界面提示）


老师取得申请表列表：
路径：/user/tprolst
方法：post
头部（不包括通用参数）：
①asin（签名验证）：md5(info= &_=timestamp)
类型：String

参数：
必须：
①_：timestamp
类型：String
解释：一个毫秒级时间戳
②token:登录操作时返回的一个令牌
可选：
无
返回：
正确：
返回成功（result的就是冲突的申请表，隐私信息已经过后台屏蔽）
错误：
异常消息（界面提示）



老师同意申请：
路径：/user/tprove
方法：post
头部（不包括通用参数）：
①asin（签名验证）：md5(info= &_=timestamp)
类型：String

参数：
必须：
①info:  rsa(一个申请信息的json)
类型：String
解释：json的键值如下

private String sheetId;

②_：timestamp
类型：String
解释：一个毫秒级时间戳
③token:登录操作时返回的一个令牌
可选：
无
返回：
正确：
返回成功（result的就是冲突的申请表，隐私信息已经过后台屏蔽）
错误：
异常消息（界面提示）



管理员老师取得申请表列表：
路径：/user/aprolst
方法：post
头部（不包括通用参数）：
①asin（签名验证）：md5(info= &_=timestamp)
类型：String

参数：
必须：
①_：timestamp
类型：String
解释：一个毫秒级时间戳
②token:登录操作时返回的一个令牌
可选：
无
返回：
正确：
返回成功（result的就是申请表列表）
错误：
异常消息（界面提示）



管理员老师同意申请：
路径：/user/aprove
方法：post
头部（不包括通用参数）：
①asin（签名验证）：md5(info= &_=timestamp)
类型：String

参数：
必须：
①info:  rsa(一个申请信息的json)
类型：String
解释：json的键值如下

private String sheetId;

②_：timestamp
类型：String
解释：一个毫秒级时间戳
③token:登录操作时返回的一个令牌
可选：
无
返回：
正确：
返回成功
错误：
异常消息（界面提示）

