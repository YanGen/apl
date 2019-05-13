package com.muhuan.Service.pojo.flow;

import java.io.Serializable;
import java.util.Date;

/**
 * @author young
 * @ClassName: PostParam
 * @Description: TODO()
 * @date 2019/4/23 15:54
 */
public class PostParam implements Serializable {

    private String info;
    private String _;
    private String token;

    @Override
    public String toString() {
        return "PostParam{" +
                "info='" + info + '\'' +
                ", _='" + _ + '\'' +
                '}';
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String get_() {
        return _;
    }

    public void set_(String _) {
        this._ = _;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
