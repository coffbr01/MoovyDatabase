package com.sdg.moovydatabase;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Get ListView and set OnItemClickListener will launch DetailsActivity on tap, passing the Moovy as an extra on the intent.

        // Create and execute an AsyncTask for the movie list using this URL:
        // http://api.rottentomatoes.com/api/public/v1.0/lists/movies/box_office.json?limit=36&country=us&apikey=mvfvxf3yz5vprh3xewzua32h
        // The AsyncTask (after response received) should create a MoovyListAdapter, add the Moovies to it, and tie it to the ListView.
    }
}
