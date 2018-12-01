package com.app.xeross.mynews.Model.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.xeross.mynews.Model.Articles.Articles;
import com.app.xeross.mynews.Model.Articles.Result;
import com.app.xeross.mynews.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context context;
    List<Result> articles = new ArrayList<>();

    // Constructor
    public RecyclerViewAdapter(Context c) {
        context = c;
    }

    // Return the size of the list
    @Override
    public int getItemCount() {
        if (articles != null) {
            return articles.size();
        }
        return 0;
    }

    public Result getTests(int position) {
        return this.articles.get(position);
    }

    // RecyclerView update
    public void updateAnswers(Articles items) {
        articles.clear();
        articles.addAll(items.getResults());
        notifyDataSetChanged();
    }

    // Creation new views
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_cell, parent, false);
        return new MyViewHolder(view);
    }

    // Place the contents of a view
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(articles.get(position).getSection());
        holder.description.setText(articles.get(position).getTitle());
        holder.date.setText(articles.get(position).getPublishedDate());

        try {
            Picasso.with(context).load(articles.get(position).getMultimedia().get(0).getMediaMetadata().get(0).getUrl()).into(holder.image);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Gets a reference to the views for each data item
    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView title, description, date;
        private final ImageView image;

        public MyViewHolder(final View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.textview_title);
            description = itemView.findViewById(R.id.textview_description);
            image = itemView.findViewById(R.id.imageview_recyclerview);
            date = itemView.findViewById(R.id.textview_date);


        }

    }

}
