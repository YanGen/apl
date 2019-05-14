// pages/admin_apply/admin_apply.js
var app = getApp();
var url
Page({

  /**
   * 页面的初始数据
   */
  data: {
    massges: [],
    id: -1
  },
  check: function (e) {

    wx.navigateTo({
      url: '../admin-examine-apply/admin-examine-apply?id='+e.currentTarget.dataset.text,
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onLoad: function () {
    var token = app.globalData.token
    var timestamp = Date.parse(new Date());
    var data = { token: token, _: timestamp };
    var that = this
    if(app.globalData.power==2)
    {
      url="http://114.115.182.110:8000/user/tprolst"
    }
    if(app.globalData.power==3)
    {
      url="http://114.115.182.110:8000/user/aprolst"
    }
    
    wx.request({
      url: url,
      method: "POST",
      header: {
        "content-type": "application/json"
      },
      data: JSON.stringify(data),
      success: function (data) {
        // if(data.data.massage == "请登录！"){
        //   wx.navigateTo({
        //     url: '../index/index',
        //   })
        //   return
        // }
        that.setData({
          massges: data.data.results
        })
        // console.log('这是管理员获取到的data')
        console.log(data);  //在控制台打印服务器端返回的数据
      },
      error: function (data) {
        console(data);
      }
    })
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