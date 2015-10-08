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
public class GithubRequest extends AsyncTask<String, Integer,List<GithubRequest.Contributor>>{
    public static final String API_URL = "https://api.github.com";

    private final Context context;
    private final GitHub github;

    public static class Contributor {
        public final String login;
        public final int contributions;

        public Contributor(String login, int contributions) {
            this.login = login;
            this.contributions = contributions;
        }
    }

    public interface GitHub {
        @GET("/repos/{owner}/{repo}/contributors")
        Call<List<Contributor>> contributors(
                @Path("owner") String owner,
                @Path("repo") String repo);
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

    private List<Contributor> requestGetAllContributors(){
        // Create a call instance for looking up Retrofit contributors.
        Call<List<Contributor>> call = github.contributors("square", "retrofit");

        // Fetch and print a list of the contributors to the library.
        List<Contributor> contributors = null;
        try {
            contributors = call.execute().body();
        } catch (IOException e) {
            Log.e("Tomek","Exception when calling Retro request"+e.getStackTrace().toString());
            e.printStackTrace();
        }

        for (Contributor contributor : contributors) {
            Log.d("Tomek",contributor.login + " (" + contributor.contributions + ")");
        }

        return contributors;
    }

    @Override
    protected List<Contributor> doInBackground(String... strings) {

        List<Contributor> contributors = requestGetAllContributors();

        return contributors;
    }


    @Override
    protected void onPostExecute(List<Contributor> contributors) {
        Activity act = (Activity) context;

        List<Map<String,String>> usersList = convertContributorListToUsersList(contributors);

        ((MainActivity)act).setUsersList(usersList);

        String[] from = new String[] {"userName","userId"};
        int [] to = new int[] {android.R.id.text1,android.R.id.text2};


        SimpleAdapter newAdapter = new SimpleAdapter(context,usersList, android.R.layout.simple_list_item_activated_2,from,to);

        ((MainActivity) act).setListAdapter(newAdapter);

    }

    private List<Map<String, String>> convertContributorListToUsersList(List<Contributor> contributors) {

        List<Map<String,String>> listUsers = new LinkedList<Map<String,String>>();

        for(Contributor contr: contributors){
            Map<String,String> map = new HashMap<>();
            map.put("userId",Integer.valueOf(contr.contributions).toString());
            map.put("userName",contr.login);
            listUsers.add(map);
        }

        return listUsers;
    }
}
