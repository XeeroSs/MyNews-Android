package com.app.xeross.mynews.Model.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.xeross.mynews.Model.MostPopular.ApiModelMostPopular;
import com.app.xeross.mynews.Model.MostPopular.Result;
import com.app.xeross.mynews.R;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Context context;
    List<Result> mApiModelMostPopularList = new ArrayList<>();

    // Constructor
    public CustomAdapter(Context c) {
        context = c;
    }

    // Return the size of the list
    @Override
    public int getItemCount() {
        if (mApiModelMostPopularList != null) {
            return mApiModelMostPopularList.size();
        }
        return 0;
    }

    // RecyclerView update
    public void updateAnswers(ApiModelMostPopular items) {
        mApiModelMostPopularList.clear();
        mApiModelMostPopularList.addAll(items.getResults());
        notifyDataSetChanged();
    }

    // Creation new views
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_cell, parent, false);
        return new MyViewHolder(view);
    }

    // Place the contents of a view
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(mApiModelMostPopularList.get(position).getSection());
        holder.description.setText(mApiModelMostPopularList.get(position).getTitle());
        holder.date.setText(mApiModelMostPopularList.get(position).getPublishedDate());

        try {
            URL url = new URL(mApiModelMostPopularList.get(position).getMultimedia().get(0).getUrl());
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            holder.image.setImageBitmap(bmp);
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
