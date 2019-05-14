// pages/apply/apply.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    massges:[
    ],
  },



  check: function(e){
    console.log(e.currentTarget.dataset.text)
    wx.navigateTo({
      url: '../stu-check-apply/stu-check-apply?id='+e.currentTarget.dataset.text,
    })
  },
/**
 *      "classroomArea2": 1,// 楼宇类型id 例如 西学楼的id 教学楼的id
      "classroomArea3": 3,// 第几栋 3 是3号的意思
      "classroomArea4": "191", // 111室咯
 */
  UserApplyList:function(datas){
    var that = this
    let massges = new Array()
    let bulid='-1'
    for(let i in datas){
        if(datas[i].building==1){
          bulid='行政楼'
        }
        if(datas[i].building==2){
          bulid='西学楼'
        }
        if(datas[i].building==3){
          bulid='东学楼'
        }
        if(datas[i].building==4){
          bulid='教学楼'
        }
        massges.push(
          {
            id:datas[i].id,
            date:datas[i].start,
            room:bulid+datas[i].buildingNumber+'栋'+datas[i].roomNumber,
            status:datas[i].state
          }
        )
           
    }
    that.setData({
      massges:massges,
    })
    console.log('this.data.massges')
    console.log(this.data.massges)
  },

  getDatas:function(){
    var token = app.globalData.token
    var timestamp = Date.parse(new Date());
    var data = {token:token,_:timestamp};
    var that = this
    wx.request({
      url: "http://114.115.182.110:8000//user/hislst",
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
        that.UserApplyList(data.data.results)
        // that.setData({
        //   massges : data.data.results
        // })
        console.log("apply连接成功")
        console.log(data);  //在控制台打印服务器端返回的数据
      },
      error: function (data) {
        console.log("apply失败")
        console(data);
      }
    })

  },

  getRoom(){

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
var that = this
that.getDatas()
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