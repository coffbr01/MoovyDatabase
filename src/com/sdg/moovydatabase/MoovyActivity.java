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

public class MoovyActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moovy);
        Moovy moovy = (Moovy) getIntent().getSerializableExtra("moovy");
        ImageView iv = (ImageView) findViewById(R.id.imageView);
        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText(moovy.getSynopsis());

        new FetchTask().execute(moovy.getPosterOriginal());
    }

    private class FetchTask extends AsyncTask<String, Void, Drawable> {
        @Override
        protected Drawable doInBackground(String... urls) {
            InputStream is = null;
            try {
                is = (InputStream) new URL(urls[0]).getContent();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return Drawable.createFromStream(is, null);
        }

        @Override
        protected void onPostExecute(Drawable d) {
            ImageView iv = (ImageView) MoovyActivity.this.findViewById(R.id.imageView);
            iv.setImageDrawable(d);
        }
    }
}
