package com.pum.tomasz.androidgithubfetchtask;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
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
public class GithubRequest extends AsyncTask<String,String,List<GithubUser>>{
    private static final String GITHUB_API = "https://api.github.com";
    Context context;
    private final GithubService service;
    private List<GithubUser> usersList;


    public interface GithubService{
        @GET("/users")
        Call<List<GithubUser>> listUsers();
    }

    public GithubRequest(Context context) {
        this.context = context;

        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(GITHUB_API).addConverterFactory(GsonConverterFactory.create()).
                build();

        service = retrofit.create(GithubService.class);

    }



    @Override
    protected List<GithubUser> doInBackground(String... params) {
        Call<List<GithubUser>> call = service.listUsers();

        List<GithubUser> list = null;
        try {
            list = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }


    @Override
    protected void onPostExecute(List<GithubUser> githubUsers) {
        ((MainActivity)context).setAllUsersList(githubUsers);

        for(GithubUser user:githubUsers){
            Log.d("Tomek", "user: " + user.getLogin());
        }

        ((ArrayAdapter)((MainActivity)context).getAdapter()).addAll(githubUsers);


        ((MainActivity)context).getAdapter().notifyDataSetChanged();

    }

}
