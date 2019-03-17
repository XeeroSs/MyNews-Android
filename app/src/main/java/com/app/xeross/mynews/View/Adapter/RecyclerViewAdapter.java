package com.app.xeross.mynews.View.Adapter;

/**
 * Created by XeroSs on 24/12/2018.
 */

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.xeross.mynews.Model.Articles.ArticlesMost;
import com.app.xeross.mynews.Model.Articles.ArticlesSearch;
import com.app.xeross.mynews.Model.Articles.ArticlesTop;
import com.app.xeross.mynews.R;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    List<ArticlesTop.Result> articlesTop;
    List<ArticlesSearch.Doc> articlesSearch;
    List<ArticlesMost.Result> articlesMost;
    Context context;

    // Constructor
    public RecyclerViewAdapter(Context c,
                               @Nullable List<ArticlesTop.Result> articlesTop,
                               @Nullable List<ArticlesSearch.Doc> articlesSearch,
                               @Nullable List<ArticlesMost.Result> articlesMost) {
        this.context = c;
        this.articlesTop = articlesTop;
        this.articlesSearch = articlesSearch;
        this.articlesMost = articlesMost;
    }

    // Return the size of the list
    @Override
    public int getItemCount() {
        if (articlesSearch != null) {
            return articlesSearch.size();
        } else if (articlesTop != null) {
            return articlesTop.size();
        } else if (articlesMost != null) {
            return articlesMost.size();
        }
        return 0;
    }

    // Gets position the list articlesTop
    public ArticlesTop.Result getPositionTop(int position) {
        return this.articlesTop.get(position);
    }

    // Gets position the list articlesSearch
    public ArticlesSearch.Doc getPosition(int position) {
        return this.articlesSearch.get(position);
    }

    // Gets position the list articlesMost
    public ArticlesMost.Result getPositionMost(int position) {
        return this.articlesMost.get(position);
    }

    // Add all items of artclesTop in RecyclerView
    public void updateAnswersTop(ArticlesTop items) {
        articlesTop.addAll(items.getResults());
        notifyDataSetChanged();
    }

    // Clear List
    public void clearTopList() {
        articlesTop.clear();
        notifyDataSetChanged();
    }

    // Add all items of articlesSearch in RecyclerView
    public void updateAnswersSearch(ArticlesSearch items) {
        articlesSearch.clear();
        articlesSearch.addAll(items.getResponse().getDocs());
        notifyDataSetChanged();
    }

    // Add all items of articlesMost in RecyclerView
    public void updateAnswersMost(ArticlesMost items) {
        articlesMost.clear();
        articlesMost.addAll(items.getResults());
        notifyDataSetChanged();
    }

    // Gets the list of articlesTop
    public List<ArticlesTop.Result> items() {
        return articlesTop;
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

        // -------------------- ArticleSearch --------------------
        if (articlesSearch != null) {
            holder.title.setText(context.getResources().getString(R.string.section) + articlesSearch.get(position).getSectionName());
            holder.description.setText(articlesSearch.get(position).getHeadline().getMain());
            holder.date.setText(articlesSearch.get(position).getPubDate());

            // String holding the articles url
            List<ArticlesSearch.Multimedium> listSearch = articlesSearch.get(position).getMultimedia();

            if (!listSearch.isEmpty()) {
                Picasso.with(context).load(context.getResources().getString(R.string.urlimage) + listSearch.get(0).getUrl()).into(holder.image);
            } else {
                holder.image.setVisibility(View.INVISIBLE);
            }

            // -------------------- TopStories --------------------
        } else if (articlesTop != null) {
            holder.title.setText(context.getResources().getString(R.string.section) + articlesTop.get(position).getSection());
            holder.description.setText(articlesTop.get(position).getTitle());
            holder.date.setText(articlesTop.get(position).getPublishedDate());

            Collections.sort(articlesTop, new Comparator<ArticlesTop.Result>() {
                @Override
                public int compare(ArticlesTop.Result o1, ArticlesTop.Result o2) {
                    return o2.getPublishedDate().compareTo(o1.getPublishedDate());
                }
            });

            // String holding the articles url
            List<ArticlesTop.Multimedium> listTop = articlesTop.get(position).getMultimedia();

            if (!listTop.isEmpty()) {
                Picasso.with(context).load(listTop.get(0).getUrl()).into(holder.image);
            } else {
                holder.image.setVisibility(View.INVISIBLE);
            }

            // -------------------- MostPopular --------------------
        } else if (articlesMost != null) {
            holder.title.setText(context.getResources().getString(R.string.section) + articlesMost.get(position).getSection());
            holder.description.setText(articlesMost.get(position).getTitle());
            holder.date.setText(articlesMost.get(position).getPublishedDate());

            // String holding the articles url
            List<ArticlesMost.Medium> listMost1 = articlesMost.get(position).getMedia();

            if (!listMost1.isEmpty()) {
                List<ArticlesMost.MediaMetadatum> listMost2 = listMost1.get(0).getMediaMetadata();
                if (!listMost2.isEmpty()) {
                    Picasso.with(context).load(listMost2.get(0).getUrl()).into(holder.image);
                } else {
                    holder.image.setVisibility(View.INVISIBLE);
                }
            } else {
                holder.image.setVisibility(View.INVISIBLE);
            }
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
