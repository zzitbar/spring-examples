package com.coderme.example1.customtag;

/**
 * Project:health-backend
 * Created By zhang tengfei
 * Date:2017/4/14
 * Time:13:49
 */
public class MyBean {

    private Long userid;
    private String username;
    private String usercode;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public MyBean() {
    }

    public MyBean(Long userid, String username, String usercode) {
        this.userid = userid;
        this.username = username;
        this.usercode = usercode;
    }
}
