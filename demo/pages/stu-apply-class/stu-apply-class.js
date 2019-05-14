// pages/stu-apply-class/stu-apply-class.js
var app = getApp()
var util = require('../../utils/util.js');
import WxValidate from "../../utils/wxValidate";
Page({

  /**
   * 页面的初始数据
   */
  //选择日期时，选择器显示

  data: {
    classroomAreaDatas: null,
    adminDatas: null,
    id: null,
    hitPicker: true,
    roomValue: '',
    mediaValue: '有',
    useStartDate: '',
    useStartTimeDe: '08:00',
    useStartTime: '',
    useEndDate: '',
    useEndTimeDe: '08:00',
    useEndTime: '',
    startDateChose: '',
    scrollTop: 100,
    adminValue: '',
    multiArray: [],
    multiIndex: 0,
    classTypes: [],
    buildNumbers: [],
    roomMultiArray: [[], []],
    roomMultiIndex: [0, 0],
    onAdminInit: true,
    onRoomInit: true,
    reasonHid: false,
    classTypeId: -1,
    buildNumber: -1,
    teacherid: 162011,
    teacherName: '我是老师',
    // WxValidate: null,//验证表单
  },

  //后端连接开始

  /*
  "classroomArea1": "教学区域",
  
  
  */

  //表单验证
  formVerify: function () {
    var that = this

    let rules = {

      principalName: {
        required: true,
        minlength: 2,
        maxlength: 10,
      },

      phone: {
        required: true,
        tel: true,
      },

      roomType: {
        required: true,
      },

      roomNumber: {
        required: true,
        minlength: 2,
        maxlength: 4,
      },

      useStartDate: {
        required: true,
      },

      useEndDate: {
        required: true,
      },

      useStartTime: {
        required: true,
      },

      reason: {
        required: true,
      },

    }

    let message = {

      principalName: {
        required: '请输入负责人姓名',
      },

      phone: {
        required: '请输入准确的手机号码',
      },

      roomType: {
        required: '请输入申请教室楼栋',
      },

      roomNumber: {
        required: '请输入教室号（ps：305）',
      },

      useStartDate: {
        required: '请输入开始日期',
      },

      useEndDate: {
        required: '请输入结束日期',
      },

      useStartTime: {
        required: '请输入开始时间',
      },

      reason: {
        required: '请输入结束时间',
      },

    }
    //实例化当前的验证规则和提示消息
    that.WxValidate = new WxValidate(rules, message);

  },

  formSubmit(e) {
    this.formVerify()
    if (!this.WxValidate.checkForm(e)) {
      //表单元素验证不通过，此处给出相应提示
      let error = this.WxValidate.errorList[0];
      wx.showModal({
        title:'错误提示',
        content:error.msg
      })
    }
    else{
    // wx.showToast({
    //   title: '成功',
    //   icon: 'succes',
    //   duration: 3000,
    //   mask: true
    // })
    this.posts(e)  
  }
    // wx.redirectTo({
    //   url: '../apply/apply',   //注意switchTab只能跳转到带有tab的页面，不能跳转到不带tab的页面
    // })

    // }
  },



  posts: function (e) {
    var that = this
    var timestamp = Date.parse(new Date());
    var sheet = {
      // "classroomArea1": "教学区域",
      // "classroomArea2": 1,// 楼宇类型id 例如 西学楼的id 教学楼的id
      // "classroomArea3": 3,// 第几栋 3 是3号的意思
      // "classroomArea4": "310", // 111室咯
      // "reason": "理由",
      // "startTime": "2019-05-27 20:45:30",
      // "endTime": "2019-05-28 21:50:30",
      // "applyPerson": "陈同学",
      // "applyPersonPhone": "15107683599",
      // "teacherCode": "162011",
      // "teacherName": "李五",
       "classroomArea2": that.data.classTypeId + '',// 楼宇类型id 例如 西学楼的id 教学楼的id
        "classroomArea3": that.data.buildNumber + '',// 第几栋 3 是3号的意思
        "classroomArea4": e.detail.value.roomNumber + '', // 111室咯
        "reason": e.detail.value.reason + '',
        "startTime": e.detail.value.useStartDate + " " + e.detail.value.useStartTime+':00',
        //  "startTime": that.nowTimeDate(),
        //  "startTime": "2019-03-29 08:45:30",
        "endTime": e.detail.value.useEndDate + " " + e.detail.value.useEndTime+':00',
        "applyPerson": e.detail.value.principalName + '',
        "applyPersonPhone": e.detail.value.phone + '',
        "teacherid": that.data.teacherid + '',
        "teacherName": that.data.teacherName + '',
    };
    var data = {
      info: JSON.stringify(sheet),
      _: timestamp,
      token: app.globalData.token,
    };
    that.postData(data)
    /**
     *  //查询是否冲突
        wx.request({
          method: 'POST',
          url: "http://114.115.182.110:8000/user/check",
          header: {
            "content-type": "application/json"
          },
          data: JSON.stringify(data),
          success: function (data) {
            if (data.data.result == false) {
              console.log('成功提交')
              that.postData()
            }
            else {
              console.log('教室冲突')
            }
          },
          error: function (data) {
            console(data)
          }
        })
     */
  },

  //传输数据到后台
  postData: function (data) {
    wx.request({
      method: 'POST',
      url: "http://114.115.182.110:8000/user/apply",
      header: {
        "content-type": "application/json"
      },
      data: JSON.stringify(data),
      success: function (data) {
        // console.log(that.data.startTime)
        console.log('连接申请成功')
        
        if(data.data.massage == "时间冲突~"){
          wx.showToast({
            title: '教室冲突！',
            icon: "none"
          }
          )
        }
        else if(data.data.massage == "请登录！"){
          wx.navigateTo({
            url: '../index/index',
          })
          return
        }
        else{
          wx.showToast({
            title: '申请成功！',
          }
          )
          wx.navigateTo({
            url: '../apply/apply',
          })
        }

        console.log(data)
      },
      error: function (data) {
        console(data)
      }
    })
  },

  // 获取楼宇信息  开始


  //拿到楼宇信息
  getRoom: function () {
    var that = this
    wx.request({
      type: 'GET',
      url: 'http://114.115.182.110:8000/system/build',
      header: {
        "content-type": "application/json"
      },
      success: function (data) {
        console.log(data);
        console.log('来到了拿楼宇的数据一开始')
        that.setData({
          classroomAreaDatas: data.data.result
        })
        that.getBulidDataInit()
      },
      error: function (data) {
        console(data);
      }
    })
  },

  //选择教室类型时，选择楼栋
  getBulids: function (roomTypeNum) {
    // let build = new Array()
    let classroomAreaDatas = this.data.classroomAreaDatas
    let bulidNames = new Array()
    let roomMultiArray = this.data.roomMultiArray
    for (let j = 0; j < classroomAreaDatas[roomTypeNum].buildingItems.length; j++) {
      bulidNames.push(
        classroomAreaDatas[roomTypeNum].buildingItems[j].buildingItem
      )

    }
    roomMultiArray[1] = bulidNames
    this.setData({
      roomMultiArray: roomMultiArray
    })
    console.log(roomMultiArray)
    return bulidNames

  },

  //拿到楼宇数据时的函数
  getBulidDataInit: function () {
    console.log('来到了拿楼宇的数据')
    let classroomAreaDatas = this.data.classroomAreaDatas
    console.log(classroomAreaDatas);
    let build = new Array()
    let typeNames = new Array()
    let bulidNames = new Array()
    for (let i = 0; i < classroomAreaDatas.length; i++) {
      typeNames.push(
        classroomAreaDatas[i].name
      )
    }

    //初始化滚动选择器(楼栋)
    bulidNames = this.getBulids(0)
    this.setData({
      buildNumbers: build,
    })
    this.setData({
      roomMultiArray: [typeNames, bulidNames]
    })
    console.log(this.data.roomMultiArray)
  },

  // 获取楼宇信息  结束

  //获取一级审批员信息 开始
  getAdmin: function () {
    var that = this
    // 获取老师信息的例子
    // 登陆时取得的令牌
    var data = { token: getApp().globalData.token, _: getApp().globalData.timestamp }
    wx.request({
      method: "POST",
      // url: "http://114.115.182.110:8000/user/tprolst",
      url: "http://114.115.182.110:8000/user/tealst",
      data: JSON.stringify(data),
      header: {
        "Content-Type": "application/json"
      },
      success: function (data) {
        console.log('已经全部成功了！老师数据拿不到')
        console.log(data.data.results)
        console.log(data);  //在控制台打印服务器端返回的数据
        that.setData({
          adminDatas: data.data.results
        })
        that.getAdminDataInit()
      },
      error: function (data) {
        console.log('失败了！')
        console(data);
      }
    });
  },




  //拿到一级审批员数据时的函数
  getAdminDataInit: function () {
    let adminDatas = this.data.adminDatas
    let multiArray = this.data.multiArray

    console.log(adminDatas);
    let adminNames = new Array()
    for (let i in adminDatas) {
      // for (let i = 0; i < adminDatas.length; i++) {
      adminNames.push(
        adminDatas[i].name
      )
    }
    multiArray[0] = adminNames
    this.setData({
      multiArray: multiArray
    })
    console.log(this.data.multiArray)
  },
  //获取一级审批员信息 结束





  // 页面逻辑开始……


  //弹出框
  modalcntDate: function () {
    var that = this
    wx.showModal({
      title: '提示',
      content: '开始时间不可晚于结束时间',
      complete: that.showDate,
      dateValue: ''

    })
  },


  //日期时间选择器
  bindStartDateChange(e) {
    this.setData({
      useStartDate: e.detail.value
    })

  },

  bindEndDateChange(e) {
    if (this.data.useStartDate > e.detail.value) {
      this.modalcntDate();
    }
    else {
      this.setData({
        useEndDate: e.detail.value
      })
    }

  },

  bindStartTimeChange(e) {
    this.setData({
      useStartTime: e.detail.value + ':00'
    })
  },

  bindEndTimeChange(e) {
    if (this.data.useStartTime > e.detail.value) {
      this.modalcntDate();
    }
    else {
      this.setData({
        useEndTime: e.detail.value + ':00'
      })
    }

  },

  // 一级审批员滚动选择器，初始化数据
  onAdmin() {

    console.log("按到了一级审批员！")
    if (this.data.onAdminInit == true) {
      this.setData({
        adminValue: this.data.multiArray[0][0],
        teacherid: this.data.adminDatas[0].teacherCode,
        onAdminInit: false
      })
    }
  },

  bindMultiPickerColumnChange(e) {
    let index = e.detail.value
    this.setData({
      teacherid: this.data.adminDatas[index].teacherCode,
      teacherName: this.data.adminDatas[index].teacherName,
      adminValue: this.data.adminDatas[index].name,
    })
  },


  //选择教室滚动选择器,初始化数据
  onRoom() {
    if (this.data.onRoomInit == true) {
      this.setData({
        roomValue: this.data.roomMultiArray[1][0],
        classTypeId: this.data.classroomAreaDatas[0].id,
        buildNumber: this.data.classroomAreaDatas[0].buildingItems[0].id,
        onRoomInit: false
      })
    }
  },



  roomBindMultiPickerColumnChange(e) {
    const data = {
      roomMultiArray: this.data.roomMultiArray,
      roomMultiIndex: this.data.roomMultiIndex,
      classTypeId: this.data.classTypeId,
      // classType: this.data.classType,
      buildNumber: this.data.buildNumber,
      classroomAreaDatas: this.data.classroomAreaDatas,
    }
    //e.detail.column告诉你第几列，e.detail.value告诉你在第几行，
    //所以multiIndex[e.detail.column]就能得知当前的位置了相当于(x,y)
    data.roomMultiIndex[e.detail.column] = e.detail.value
    switch (e.detail.column) {//控制第n列
      case 0:
        switch (data.roomMultiIndex[0]) {
          case 0:
            this.getBulids(0)
            break
          case 1:
            this.getBulids(1)
            break
          case 2:
            this.getBulids(2)
            break
          case 3:
            this.getBulids(3)
            break

        }
        data.roomMultiIndex[1] = 0//第二列显示第一行
        break
    }

    // let classTypeName = data.roomMultiArray[0][data.roomMultiIndex[0]];
    let bulidName = data.roomMultiArray[1][data.roomMultiIndex[1]];
    data.buildNumber = data.roomMultiIndex[1] + 1;//拿到第几栋,楼栋的id

    let bulidIndex = data.roomMultiIndex[1]//类型的索引,因为滚动选择器的索引和获取到的数组的索引是一样的值
    let bulidId = data.classroomAreaDatas[bulidIndex].id


    let typeIdIndex = data.roomMultiIndex[0]//类型的索引
    let typeId = data.classroomAreaDatas[typeIdIndex].id

    data.classTypeId = typeId
    data.buildNumber = bulidId
    this.setData(data)
    this.setData({
      roomValue: bulidName
    })
  },


  submit: function () {

  },

  nowTimeDate: function () {
    // 调用函数时，传入new Date()参数，返回值是日期和时间  
    var time = util.formatTimeandDate(new Date());
    return time;
  },

  // 页面逻辑结束……



  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this
    var nowTime = function nowTime() {
      // 调用函数时，传入new Date()参数，返回值是日期和时间  
      var time = util.formatTime(new Date());
      return time;
    }
    that.setData({
      startDateChose: nowTime()
    })
    that.getRoom()
    that.getAdmin()
    // that.posts()
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {


  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {


  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})