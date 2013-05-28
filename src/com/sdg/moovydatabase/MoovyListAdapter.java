package com.sdg.moovydatabase;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

// This is the "controller" for the movie list. It gets the row data from each Moovy and sets
// attributes on the views (TextView, etc).
public class MoovyListAdapter extends ArrayAdapter {
    public MoovyListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the Moovy at the given position and set this row's TextView to the Moovy title.
        // Create and execute an AsyncTask to fetch the poster thumbnail image.
        return null;
    }
}
