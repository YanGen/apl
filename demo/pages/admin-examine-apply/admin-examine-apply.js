// pages/stu-check-apply/stu-check-apply.js
var RSA = require('../../utils/wx_rsa.js');//导入小程序RSA加密代码
var app = getApp()
var url
Page({

  /**
   * 页面的初始数据
   */

  /**
   * 
   *  "classroomArea2": that.data.classTypeId + '',// 楼宇类型id 例如 西学楼的id 教学楼的id
      "classroomArea3": that.data.buildNumber + '',// 第几栋 3 是3号的意思
      "classroomArea4": e.detail.value.roomNumber + '', // 111室咯
      "": e.detail.value.reason + '',
      "": e.detail.value.useStartDate + " " + e.detail.value.useStartTime,
      "": e.detail.value.useEndDate + " " + e.detail.value.useEndTime,
      "": e.detail.value.principalName + '',
      "": e.detail.value.phone + '',
      "teacherid": that.data.teacherid + '',
      "": that.data.teacherName + '',
   * 
   */
  data: {
    scrollTop: 100,
    applyPerson: '申请人',
    principalName: '陈六',
    applyPersonPhone: 15107683333,
    room: '2-202',
    startTime: '2019-10-10 13:00:00',
    endTime: '2019-10-10 14:00:00',
    teacherName: '唐老师',
    reason: '班级聚会',
    status: '等待老师批准',
    datas: null,
    userCode: 1001,
    tableId: 29,
    oneData: null,

  },

  pass() {
    var that = this
    console.log('that.data.tableId')
    console.log(that.data.tableId)
    var timestamp = Date.parse(new Date());
    var proveParam = {
      "sheetId": that.data.tableId,
      "result": true,
      "illustrate": '批准！'
    };

    //利用公钥对登录的数据进行加密
    var publicKey_pkcs1 = '-----BEGIN PUBLIC KEY-----MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDcq2rzZLzbZpu2LUUMZUkxtsACiCfKK7RrdDTzIdniZEIpcPSBvAzfS6dtbAhefhw7ynHnm2Ee7YN6lzmBpIeBapvZOqRdaOxnCzOB+ZX3yyBqyHbJGWpA8gjDFmrq/ErQUMWSqgpf6vj3A3WqoWDEyUcSoTqxyz7KzNZubJj7LQIDAQAB-----END PUBLIC KEY-----'
    var encrypt_rsa = new RSA.RSAKey();
    encrypt_rsa = RSA.KEYUTIL.getKey(publicKey_pkcs1); //publicKey_pkcs1就是公钥
    var encStr = encrypt_rsa.encrypt(JSON.stringify(proveParam)) //参数写json格式的数据
    encStr = RSA.hex2b64(encStr);
    // console.log("加密结果：" + encStr)

    var data = {
      info: encStr,
      _: timestamp,
      token: getApp().globalData.token
    }
    if(app.globalData.power==2)
    {
      url="http://114.115.182.110:8000/user/tprove"
    }
    if(app.globalData.power==3)
    {
      url="http://114.115.182.110:8000/user/aprove"
    }
    wx.request({
      url: url,
      method: "POST",
      header: {
        "content-type": "application/json"
      },
      data: JSON.stringify(data),
      success: function (data) {
        wx.showToast({
          title: '审批成功！',
        }
        )
        wx.navigateTo({
          url: '../admin_apply/admin_apply',
        })
        console.log("老师同意")
        console.log(data); //在控制台打印服务器端返回的数据

      },
      error: function (data) {
        console.log("连接失败")
        console(data);
      }
    })

  },
   no() {
    var that = this
    console.log('that.data.tableId')
    console.log(that.data.tableId)
    var timestamp = Date.parse(new Date());
    var proveParam = {
      "sheetId": that.data.tableId,
      "result": false,
      "illustrate": '驳回理由！'
    };

    //利用公钥对登录的数据进行加密
    var publicKey_pkcs1 = '-----BEGIN PUBLIC KEY-----MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDcq2rzZLzbZpu2LUUMZUkxtsACiCfKK7RrdDTzIdniZEIpcPSBvAzfS6dtbAhefhw7ynHnm2Ee7YN6lzmBpIeBapvZOqRdaOxnCzOB+ZX3yyBqyHbJGWpA8gjDFmrq/ErQUMWSqgpf6vj3A3WqoWDEyUcSoTqxyz7KzNZubJj7LQIDAQAB-----END PUBLIC KEY-----'
    var encrypt_rsa = new RSA.RSAKey();
    encrypt_rsa = RSA.KEYUTIL.getKey(publicKey_pkcs1); //publicKey_pkcs1就是公钥
    var encStr = encrypt_rsa.encrypt(JSON.stringify(proveParam)) //参数写json格式的数据
    encStr = RSA.hex2b64(encStr);
    // console.log("加密结果：" + encStr)

    var data = {
      info: encStr,
      _: timestamp,
      token: getApp().globalData.token
    }
    if(app.globalData.power==2)
    {
      url="http://114.115.182.110:8000/user/tprove"
    }
    if(app.globalData.power==3)
    {
      url="http://114.115.182.110:8000/user/aprove"
    }
    wx.request({
      url: url,
      method: "POST",
      header: {
        "content-type": "application/json"
      },
      data: JSON.stringify(data),
      success: function (data) {
        wx.showToast({
          title: '驳回成功！',
        }
        )
        wx.navigateTo({
          url: '../admin_apply/admin_apply',
        })
        console.log("老师同意")
        console.log(data); //在控制台打印服务器端返回的数据

      },
      error: function (data) {
        console.log("连接失败")
        console(data);
      }
    })

  },



  getHouduan: function () {

    var that = this
    var data = {
      _: getApp().globalData.timestamp,
      token: getApp().globalData.token
    };

    wx.request({
      url: "http://114.115.182.110:8000/user/hislst",
      method: "POST",
      header: {
        "content-type": "application/json"
      },
      data: JSON.stringify(data),
      success: function (data) {
        if(data.data.massage == "请登录！"){
          wx.navigateTo({
            url: '../index/index',
          })
          return
        }
        that.setData({
          datas: data.data.results
        })
        console.log("查询表格连接成功")
        console.log(that.data.datas);  //在控制台打印服务器端返回的数据

        that.hislst()
        console.log('该表的数据是')
        console.log(that.data)

      },
      error: function (data) {
        console(data);
      }
    })

  },

  getRoom: function (datai) {
    console.log(datai)
    console.log('datai')
    let bulid
    if (datai.building == 1) {
      bulid = '行政楼'
    }
    if (datai.building == 2) {
      bulid = '西学楼'
    }
    if (datai.building == 3) {
      bulid = '东学楼'
    }
    if (datai.building == 4) {
      bulid = '教学楼'
    }
    var room = bulid + datai.buildingNumber + '栋' + datai.roomNumber
    console.log('datai.buildingNumber')
    console.log(datai.buildingNumber)
    return room


  },

  hislst: function () {

    var that = this
    //获取该同学该表的数据
    var tableId = that.data.tableId
    var test
    for (let i in that.data.datas) {
      if (that.data.datas[i].id == tableId) {

        that.setData({
          applyPerson: that.data.datas[i].applyPerson,
          applyPersonPhone: that.data.datas[i].applyPersonPhone,
          room: that.getRoom(that.data.datas[i]),
          startTime: that.data.datas[i].start,
          endTime: that.data.datas[i].end,
          teacherName: that.data.datas[i].teacherName,
          reason: that.data.datas[i].reason,
          status: that.data.datas[i].state,
        })

        break;
      }
      else {
        console.log('查询不到该表')
      }
    }


  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this
    that.setData({
      tableId: options.id,
    })
    console.log('that.data.tableId')
    console.log(that.data.tableId)


  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    var that = this
    that.getHouduan()

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