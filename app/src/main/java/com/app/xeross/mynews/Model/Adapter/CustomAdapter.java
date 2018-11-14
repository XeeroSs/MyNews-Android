package com.app.xeross.mynews.Model.Adapter;

import android.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.xeross.mynews.R;

import java.util.Arrays;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private final List<Pair<String, String>> characters = Arrays.asList(
            Pair.create("Parmi > Fonctionnalités", "Donald Trump félicite Emmanuel Macron"),
            Pair.create("Sujets > Moment ", "Vous avez codé toute la journée, la fatigue vous gagne"),
            Pair.create("Publiés > Maquette", " faut votre dose de café pour tenir. En sortant de chez vous pour vous rendre au Starbucks du"),
            Pair.create("Articles > Maquette", "Vous décidez de la baptiser MyNews."),
            Pair.create("Sujets > Glacé", "Vous avez codé toute la journée, la fatigue vous gagne"),
            Pair.create("Retour > Maquette", " news toute faite, mais vous préférez la développer vous-même : vous pourrez ainsi déterminer les sujets qui vous intéres"),
            Pair.create("Sujets > Articles ", "Vous décidez de la baptiser MyNews."),
            Pair.create("Glacé > Publiés ", " Latte Glacé à la main devant Wikipedia, vous découvrez avec stupeur que vous avez deux wagons de re"),
            Pair.create("Sujets > Retour ", " news toute faite, mais vous préférez la développer vous-même : vous pourrez ainsi déterminer les sujets qui vous intéres"),
            Pair.create("Moment > Maquette", "Donald Trump félicite Emmanuel Macron"),
            Pair.create("Vous > Application", " faut votre dose de café pour tenir. En sortant de chez vous pour vous rendre au Starbucks du")
    );

    @Override
    public int getItemCount() {
        return characters.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_cell, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Pair<String, String> pair = characters.get(position);
        holder.display(pair);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView description;

        private Pair<String, String> currentPair;

        public MyViewHolder(final View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.textview_title);
            description = itemView.findViewById(R.id.textview_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new AlertDialog.Builder(itemView.getContext())
                            .setTitle(currentPair.first)
                            .setMessage(currentPair.second)
                            .show();
                }
            });
        }

        public void display(Pair<String, String> pair) {
            currentPair = pair;
            title.setText(pair.first);
            description.setText(pair.second);
        }
    }

}
