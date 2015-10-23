package com.pum.tomasz.androidgithubfetchtask;


import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleAdapter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class MainActivity extends ListActivity {

    private List<Map<String,String>> usersList = new LinkedList<Map<String,String>>();
    private SimpleAdapter dataAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //loadNewUser();

        GithubRequest githubTask = new GithubRequest(this);

        githubTask.execute();

        String[] from = new String[] {"userName","userId"};
        int [] to = new int[] {android.R.id.text1,android.R.id.text2};

        dataAdapter = new SimpleAdapter(this,usersList, android.R.layout.simple_list_item_activated_2,from,to);

        setListAdapter(dataAdapter);
    }

    private void loadNewUser() {
        Map<String,String> newUser = new HashMap<String,String>() ;
        newUser.put("userId","1");
        newUser.put("userName","Tomek2k4");
        usersList.add(newUser);
    }

    public void setUsersList(List<Map<String, String>> usersList) {
        this.usersList = usersList;
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

    public SimpleAdapter getMyAdapter() {
        return dataAdapter;
    }
}
