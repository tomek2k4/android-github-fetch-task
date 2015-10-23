package com.pum.tomasz.androidgithubfetchtask;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Adapter;
import android.widget.SimpleAdapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Url;

/**
 * Created by tmaslon on 2015-10-08.
 */
public class GithubRequest extends AsyncTask<String, Integer,List<GithubUser>>{
    public static final String API_URL = "https://api.github.com";

    private final Context context;
    private final GitHub github;


    public interface GitHub {
        @GET("/users")
        Call<List<GithubUser>> users();
    }



    public GithubRequest(Context ctx) {
        this.context = ctx;

        // Create a very simple REST adapter which points the GitHub API.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of our GitHub API interface.
        github = retrofit.create(GitHub.class);

    }

    private List<GithubUser> requestGetAllContributors(){
        // Create a call instance for looking up Retrofit contributors.
        Call<List<GithubUser>> call = github.users();

        // Fetch and print a list of the contributors to the library.
        List<GithubUser> users = null;
        try {
            users = call.execute().body();
        } catch (IOException e) {
            Log.e("Tomek","Exception when calling Retro request"+e.getStackTrace().toString());
            e.printStackTrace();
        }

        for (GithubUser user : users) {
            Log.d("Tomek",user.getUserName() + " (" + user.getUserId() + ")");
        }

        return users;
    }

    @Override
    protected List<GithubUser> doInBackground(String... strings) {

        List<GithubUser> users = requestGetAllContributors();

        return users;
    }


    @Override
    protected void onPostExecute(List<GithubUser> users) {
        Activity act = (Activity) context;

        List<Map<String,String>> usersList = convertContributorListToUsersList(users);

        ((MainActivity)act).setUsersList(usersList);

        String[] from = new String[] {"userName","userId"};
        int [] to = new int[] {android.R.id.text1,android.R.id.text2};


        SimpleAdapter newAdapter = new SimpleAdapter(context,usersList, android.R.layout.simple_list_item_activated_2,from,to);

        ((MainActivity) act).setListAdapter(newAdapter);

    }

    private List<Map<String, String>> convertContributorListToUsersList(List<GithubUser> users) {

        List<Map<String,String>> listUsers = new LinkedList<Map<String,String>>();

        for(GithubUser user: users){
            Map<String,String> map = new HashMap<>();
            map.put("userId",Integer.valueOf(user.getUserId()).toString());
            map.put("userName",user.getUserName());
            listUsers.add(map);
        }

        return listUsers;
    }
}
