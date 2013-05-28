package com.sdg.moovydatabase;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class DetailsActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        // Get the Moovy extra from this activity's intent, then set
        // the TextView's synopsis.
        // Create and execute an AsyncTask to fetch the poster original image.
    }
}
