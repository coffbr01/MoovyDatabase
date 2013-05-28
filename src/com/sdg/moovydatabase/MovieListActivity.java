package com.sdg.moovydatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MovieListActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MovieListActivity.this, MoovyActivity.class);
                intent.putExtra("moovy", (Moovy)parent.getAdapter().getItem(position));
                MovieListActivity.this.startActivity(intent);
            }
        });

        new FetchTask().execute("http://api.rottentomatoes.com/api/public/v1.0/lists/movies/box_office.json?limit=36&country=us&apikey=mvfvxf3yz5vprh3xewzua32h");
    }
    private class FetchTask extends AsyncTask<String, Void, List<Moovy>> {
        @Override
        protected List<Moovy> doInBackground(String... urls) {
            List<Moovy> moovies = new ArrayList<Moovy>();
            try {
                InputStream is = new URL(urls[0]).openStream();
                try {
                    BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                    StringBuilder sb = new StringBuilder();
                    int cp;
                    while ((cp = rd.read()) != -1) {
                        sb.append((char) cp);
                    }
                    String jsonText = sb.toString();
                    JSONObject json = new JSONObject(jsonText);
                    JSONArray movies = json.getJSONArray("movies");
                    for (int i = 0; i < movies.length(); i++) {
                        JSONObject movie = movies.getJSONObject(i);
                        Moovy moovy = Moovy.parse(movie);
                        moovies.add(moovy);
                    }
                } finally {
                    is.close();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return moovies;
        }

        @Override
        protected void onPostExecute(List<Moovy> moovies) {
            ListView lv = (ListView) MovieListActivity.this.findViewById(R.id.listView);
            MoovyListAdapter la = new MoovyListAdapter(MovieListActivity.this, moovies);
            la.addAll(moovies);
            lv.setAdapter(la);
        }
    }
}
