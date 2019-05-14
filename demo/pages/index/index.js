var app = getApp();
var RSA = require('../../utils/wx_rsa.js');//导入小程序RSA加密代码
var loginParam = {
  "username": "",//账号
  "password": "",// 密码
  "power": 1,// 学生1老师2管理3
};
var publicKey_pkcs1=app.globalData.pk
Page({
  data: {
    selectArray: [{
      "id": "1",
      "text": "申请者"
    }, {
      "id": "2",
      "text": "老师"
    }, {
      "id": "3",
      "text": "管理员"
    }],
    modal: {
      modalHidden: true,
      modalContent: ''
    }
  },
  bindChange: function (e) {
    if (e.target.id == 'name') {
      loginParam.username = e.detail.value;
    } else {
      loginParam.password = e.detail.value;
    }
  },
  modalChange: function (modal) {
    this.setData({
      modal: modal
    });
  },
  modalClose: function () {
    var modal = {
      modalHidden: true,
      modalContent: ''
    };
    this.setData({
      modal: modal
    });
  },
  handleEventListener: function (e) {
    //将组件select传递的nowText通过e.detail.nowText来获取

  },
  getDate: function (e) {
    loginParam.power = e.detail.id + 1 + '';
  },
  login: function () {
    //学生
    // app.globalData.userCode = 162011111
    // wx.switchTab({
    //   url: '../admin/admin',
    // })
    //老师       
    // console.log('lianjiele')
    // app.globalData.userCode = 162011
    // wx.switchTab({
    //   url: '../admin/admin',
    // })
    //管理员       
    // app.globalData.userCode = 1001
    // wx.switchTab({
    //   url: '../admin/admin',
    // })
   // return

    var that = this
    var encrypt_rsa = new RSA.RSAKey();
    encrypt_rsa = RSA.KEYUTIL.getKey(publicKey_pkcs1);//publicKey_pkcs1就是公钥
    console.log('加密RSA:')
    console.log(encrypt_rsa)
    var encStr = encrypt_rsa.encrypt(JSON.stringify(loginParam))//参数写json格式的数据
    encStr = RSA.hex2b64(encStr);
    console.log("加密结果：" + encStr)
    var data = { info: encStr, _: "4684894541654" };
    var that = this

    wx.request({
      url: "xxx",
      method: "POST",
      header: {
        "content-type": "application/json"
      },
      data: JSON.stringify(data),
      success: function (data) {
        console.log('登录信息的data')
        console.log(data)
        if (data.data.massage == '账号密码有误') {
          var modal = {
            modalHidden: false,
            modalContent: '账号或密码错误'
          };
          that.modalChange(modal);
        } else if (data.data.massage == '成功!') {
          // var app = getApp();
          app.globalData.token = data.data.token
          app.globalData.userCode = loginParam.username
          app.globalData.power = loginParam.power
          if (loginParam.power == '1') {
            wx.switchTab({
              url: '../student/student',
            })
          } else if (loginParam.power == '2' || loginParam.power == '3') {
            wx.navigateTo({
              url: '../admin/admin',
            })
          }
        }
      },
      fail: function (data) {
        console.log(data);
      },
    })
  },
  submit: function () {
    if (loginParam.username == '') {
      var modal = {
        modalHidden: false,
        modalContent: '请输入用户名'
      };
      this.modalChange(modal);
    } else if (loginParam.password == '') {
      var modal = {
        modalHidden: false,
        modalContent: '请输入密码'
      };
      this.modalChange(modal);
    } else {
      this.login();
    }
  },

  /**
  * 生命周期函数--监听页面加载
  */
  onLoad: function (options) {
    // var that = this
    // // that.getHouduan()
    // that.login()

  },
})
