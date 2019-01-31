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

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    List<ArticlesTop.Result> articlesTop;
    List<ArticlesSearch.Doc> articlesSearch;
    List<ArticlesMost.Result> articlesMost;
    Context context;

    // Constructor
    public RecyclerViewAdapter(Context c, @Nullable List<ArticlesTop.Result> articlesTop, @Nullable List<ArticlesSearch.Doc> articlesSearch, @Nullable List<ArticlesMost.Result> articlesMost) {
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

    public ArticlesTop.Result getPositionTop(int position) {
        return this.articlesTop.get(position);
    }

    public ArticlesSearch.Doc getPosition(int position) {
        return this.articlesSearch.get(position);

    }

    public ArticlesMost.Result getPositionMost(int position) {
        return this.articlesMost.get(position);
    }

    // RecyclerView update
    public void updateAnswersTop(ArticlesTop items) {
        articlesTop.clear();
        articlesTop.addAll(items.getResults());
        notifyDataSetChanged();
    }

    public void updateAnswersSearch(ArticlesSearch items) {
        articlesSearch.clear();
        articlesSearch.addAll(items.getResponse().getDocs());
        notifyDataSetChanged();
    }

    public void updateAnswersMost(ArticlesMost items) {
        articlesMost.clear();
        articlesMost.addAll(items.getResults());
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

        // -------------------- ArticleSearch --------------------
        if (articlesSearch != null) {
            holder.title.setText(articlesSearch.get(position).getSectionName());
            holder.description.setText(articlesSearch.get(position).getHeadline().getMain());
            holder.date.setText(articlesSearch.get(position).getPubDate());

            try {
                Picasso.with(context).load(articlesSearch.get(position).getMultimedia().get(0).getUrl()).into(holder.image);
            } catch (Exception e) {

                e.printStackTrace();
            }

            // -------------------- TopStories --------------------
        } else if (articlesTop != null) {
            holder.title.setText(articlesTop.get(position).getSection());
            holder.description.setText(articlesTop.get(position).getTitle());
            holder.date.setText(articlesTop.get(position).getPublishedDate());

            try {
                Picasso.with(context).load(articlesTop.get(position).getMultimedia().get(0).getUrl()).into(holder.image);
            } catch (Exception e) {

                e.printStackTrace();
            }

            /*if (articlesTop.get(position).getColor() != null) {
                holder.color.setBackgroundColor(Color.parseColor(articlesTop.get(position).getColor()));
            }*/

            // -------------------- MostPopular --------------------
        } else if (articlesMost != null) {
            holder.title.setText(articlesMost.get(position).getSection());
            holder.description.setText(articlesMost.get(position).getTitle());
            holder.date.setText(articlesMost.get(position).getPublishedDate());

            try {
                Picasso.with(context).load(articlesMost.get(position).getMedia().get(0).getMediaMetadata().get(0).getUrl()).into(holder.image);
            } catch (Exception e) {

                e.printStackTrace();
            }

            /*if (articlesMost.get(position).getColor() != null) {
                holder.color.setBackgroundColor(Color.parseColor(articlesMost.get(position).getColor()));
            }*/
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
