package admin1.example.com.orderdistsapp.bean;

import java.util.List;

import admin1.example.com.orderdistsapp.NetEvents.exception.LoginRes;

/**
 * @Created by admin
 * @Created on 2018/9/7.
 **/
public class LoginResponse {

    public String state;
    public LoginRes data;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LoginRes getUserList() {
        return data;
    }

    public void setUserList(LoginRes userList) {
        this.data = userList;
    }
}
