package com.pum.tomasz.androidgithubfetchtask;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by tmaslon on 2015-10-23.
 */
public class GithubUserListAdapter extends BaseAdapter {


    List<GithubUser> userList;


    public GithubUserListAdapter(List<GithubUser> userList) {
        this.userList = userList;
    }


    public List<GithubUser> getUserList() {
        return userList;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int i) {
        return userList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }



}
