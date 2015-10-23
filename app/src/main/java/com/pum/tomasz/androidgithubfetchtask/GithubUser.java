package com.pum.tomasz.androidgithubfetchtask;

/**
 * Created by tmaslon on 2015-10-08.
 */
public class GithubUser {

    private String userId;
    private String userName;

    public GithubUser(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
