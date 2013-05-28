package com.sdg.moovydatabase;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class MoovyListAdapter extends ArrayAdapter {
    private Context context;
    List<Moovy> moovies;

    static class ViewHolder {
        public TextView text;
        public ImageView image;
    }

    public MoovyListAdapter(Context context, List<Moovy> moovies) {
        super(context, R.layout.rowitem, R.id.rowTextView);
        this.context = context;
        this.moovies = moovies;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.rowitem, parent, false);
            ViewHolder holder = new ViewHolder();
            holder.text = (TextView) rowView.findViewById(R.id.rowTextView);
            holder.image = (ImageView) rowView.findViewById(R.id.rowImageView);
            rowView.setTag(holder);
        }

        Moovy moovy = moovies.get(position);
        ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.text.setText(moovy.getTitle());
        holder.image.setImageDrawable(null);
        FetchTask task = new FetchTask(holder);
        task.execute(moovy.getPosterThumbnail());

        return rowView;
    }

    private class FetchTask extends AsyncTask<String, Void, Drawable> {
        private ViewHolder holder;
        public FetchTask(ViewHolder holder) {
            this.holder = holder;
        }
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
            holder.image.setImageDrawable(d);
        }
    }
}
