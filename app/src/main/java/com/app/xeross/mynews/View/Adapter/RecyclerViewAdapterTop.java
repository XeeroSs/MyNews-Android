package com.app.xeross.mynews.View.Adapter;

/**
 * Created by XeroSs on 24/12/2018.
 */

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.xeross.mynews.Model.Articles.Articles;
import com.app.xeross.mynews.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterTop extends RecyclerView.Adapter<RecyclerViewAdapterTop.MyViewHolder> {

    Context context;
    List<Articles.Result> articlesTop = new ArrayList<>();
    List<Articles.Doc> articles = new ArrayList<>();

    // Constructor
    public RecyclerViewAdapterTop(Context c, @Nullable List<Articles.Result> articlesTop, @Nullable List<Articles.Doc> article) {
        context = c;
        this.articlesTop = articlesTop;
        this.articles = articles;
    }

    // Return the size of the list
    @Override
    public int getItemCount() {
        if (articles != null) {
            return articles.size();
        }
        return 0;
    }

    public Articles.Result getPositionTop(int position) {
        return this.articlesTop.get(position);

    }

    public Articles.Doc getPosition(int position) {
        return this.articles.get(position);
    }

    // RecyclerView update
    public void updateAnswersTop(Articles items) {
        articlesTop.clear();
        articlesTop.addAll(items.getResult());
        notifyDataSetChanged();
    }

    public void updateAnswers(Articles items) {
        articles.clear();
        articles.addAll(items.getResponse().getDocs());
        notifyDataSetChanged();
    }

    // Creation new views
    @Override
    public RecyclerViewAdapterTop.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_cell, parent, false);
        return new MyViewHolder(view);
    }

    // Place the contents of a view
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        if (articles != null) {
            holder.title.setText(articles.get(position).getSection());
            holder.description.setText(articles.get(position).getTitle());
            holder.date.setText(articles.get(position).getPublishedDate());
        } else if (articlesTop != null) {
            holder.title.setText(articlesTop.get(position).getSection());
            holder.description.setText(articlesTop.get(position).getTitle());
            holder.date.setText(articlesTop.get(position).getPublishedDate());

            try {
                Picasso.with(context).load(articlesTop.get(position).getMultimedias().get(0).getUrl()).into(holder.image);
            } catch (Exception e) {

                e.printStackTrace();
            }

            if (articlesTop.get(position).getColor() != null) {
                holder.color.setBackgroundColor(Color.parseColor(articlesTop.get(position).getColor()));
            }
        }

    }

    // Gets a reference to the views for each data item
    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView title, description, date;
        private final ImageView image, color;

        public MyViewHolder(final View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.textview_title);
            description = itemView.findViewById(R.id.textview_description);
            image = itemView.findViewById(R.id.imageview_recyclerview);
            date = itemView.findViewById(R.id.textview_date);
            color = itemView.findViewById(R.id.framelayout_color);


        }

    }

}
