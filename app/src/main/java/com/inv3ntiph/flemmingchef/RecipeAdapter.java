package com.inv3ntiph.flemmingchef;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    ArrayList<Recipe> recipelist;

    public RecipeAdapter(ArrayList<Recipe> recipelist) {
        this.recipelist= recipelist;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View ItemView = LayoutInflater.from(context).inflate(R.layout.recipe_list_item,parent,false);

        return new RecipeViewHolder(ItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = recipelist.get(position);
        holder.bind(recipe);

    }

    @Override
    public int getItemCount() {
        return recipelist.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder{

        TextView RecipeTitle;
        TextView SourceName;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            RecipeTitle = itemView.findViewById(R.id.TvTitle);
            SourceName = itemView.findViewById(R.id.TvSource);
        }

        public void bind(Recipe recipe){
            RecipeTitle.setText(recipe.label);
            SourceName.setText(recipe.source);

        }
    }

}
