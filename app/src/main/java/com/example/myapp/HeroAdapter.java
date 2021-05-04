package com.example.myapp;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.HeroViewHolder> implements Filterable {

    private Context context;
    private List<Hero> heroes;
    private List<Hero> heroesFull;

    public HeroAdapter(Context context, List<Hero> heroes) {
        this.context = context;
        this.heroes = heroes;
        this.heroesFull = new ArrayList<>(heroes);
    }

    @NonNull
    @Override
    public HeroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hero_layout, parent, false);
        HeroViewHolder heroViewHolder = new HeroViewHolder(view);
        return heroViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HeroViewHolder holder, int position) {

        Hero hero = heroes.get(position);

        Glide.with(context).load(hero.getImageUrl()).into(holder.imageView);

        holder.name.setText(hero.getName());
        holder.info.setText(hero.getInfo());

    }

    @Override
    public int getItemCount() {
        return heroes.size();
    }

    public class HeroViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name;
        TextView info;

        public HeroViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.hero_image);
            name = itemView.findViewById(R.id.hero_name);
            info = itemView.findViewById(R.id.hero_info);

        }
    }

    @Override
    public Filter getFilter() {
        return heroesFilter;
    }

    private Filter heroesFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Hero> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(heroesFull);
            } else {
                String filteredPattern = constraint.toString().toLowerCase().trim();
                for (Hero hero : heroesFull) {
                    if(hero.getName().toLowerCase().contains(filteredPattern)) {
                        filteredList.add(hero);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            heroes.clear();
            heroes.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };
}
