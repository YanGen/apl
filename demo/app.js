//app.js
App(
  {
 
  onLaunch: function () {
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
      }
    })
    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              // 可以将 res 发送给后台解码出 unionId
              this.globalData.userInfo = res.userInfo

              // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // 所以此处加入 callback 以防止这种情况
              if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
              }
            }
          })
        }
      }
    })
  },
  globalData:{
    token: "PIujwy_UOMtQLb8I7EiQFn6kcT2NJDZn2iLILy9WgQpqVJVOow2r4jS-yFewYEG-LZjufx1-Yt4X9wkeSmeskDbXRwwb3uD18GTY5nuwP1aI9x3OR9taX0l1BM1oEkOc5Y0EOa8Im41SeJWT8TrQPz7dLmLkOkVJkacfKTdNWeAcD-bKrmFNDUt3RPt3rOdsNaJGCvsI-vvuD6-DlYmi01BVxum1knd-yjJ1vFPz7rJfsWUR9K8BZu01Aew8l2bI1yxwc9-JS_F0ZzBMipwoAUCty2_WiaYvzkTyV9uto7uUcri6Ildyb9B2jYNeDAjBGRFnC6TZaS22q2RnUc-2Ug",
    userCode:1001,
    pk:'-----BEGIN PUBLIC KEY-----MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDcq2rzZLzbZpu2LUUMZUkxtsACiCfKK7RrdDTzIdniZEIpcPSBvAzfS6dtbAhefhw7ynHnm2Ee7YN6lzmBpIeBapvZOqRdaOxnCzOB+ZX3yyBqyHbJGWpA8gjDFmrq/ErQUMWSqgpf6vj3A3WqoWDEyUcSoTqxyz7KzNZubJj7LQIDAQAB-----END PUBLIC KEY-----',
    timestamp:Date.parse(new Date()),
    power:-1
  },
})