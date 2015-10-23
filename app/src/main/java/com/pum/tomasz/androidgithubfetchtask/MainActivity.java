package com.pum.tomasz.androidgithubfetchtask;


import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class MainActivity extends Activity {

    private List<GithubUser> allUsersList = new LinkedList<>();
    private BaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GithubUser user = new GithubUser();
        user.setLogin("Tomek2k4");
        allUsersList.add(user);

        ListView userListView = (ListView) findViewById(R.id.github_user_list);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, allUsersList);

        userListView.setAdapter((ListAdapter) adapter);

        GithubRequest listAllUsersTask = new GithubRequest(this);
        listAllUsersTask.execute();
    }

    public List<GithubUser> getAllUsersList() {
        return allUsersList;
    }

    public void setAllUsersList(List<GithubUser> allUsersList) {
        this.allUsersList = allUsersList;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public BaseAdapter getAdapter() {
        return adapter;
    }
}
