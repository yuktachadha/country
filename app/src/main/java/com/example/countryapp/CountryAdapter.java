package com.example.countryapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


//import com.ahmadrosid.svgloader.SvgLoader;
//import com.squareup.picasso.Picasso;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryHolder>{


    private Context context;
    private List<Country> countryList;

    public CountryAdapter(Context context, List<Country> countryList){
        this.context=context;
        this.countryList=countryList;
    }
    @NonNull
    @Override
    public CountryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

  return new CountryHolder(LayoutInflater.from(parent.getContext())
          .inflate(R.layout.each_row,parent ,false));

    }

    @Override
    public void onBindViewHolder(@NonNull CountryHolder holder, int position) {


        Country country = countryList.get(position);

//        Glide.with(context)
//                .load(country.getFlag())
//                .into(holder.flag);

        holder.name.setText(country.getName());
        holder.capital.setText(country.getCapital());
        holder.region.setText(country.getRegion());
        holder.subregion.setText(country.getSubregion());
        holder.population.setText(country.getPopulation());

        String languages ="";
        for (Language language :country.getLanguages()){
            if (!languages.isEmpty())
                languages += ", ";
            languages += language.getName();
        }
    holder.languages.setText(languages);

    }
public void getAll(List<Country> countryList){
        this.countryList=countryList;
}
    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public static class CountryHolder extends RecyclerView.ViewHolder{
        public TextView name,capital,region,subregion,population,languages;
        public ImageView flag;
        public CountryHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            capital = itemView.findViewById(R.id.capital);
            region = itemView.findViewById(R.id.region);
            subregion = itemView.findViewById(R.id.subregion);
            population = itemView.findViewById(R.id.population);
            languages =itemView.findViewById(R.id.languages);

            capital = itemView.findViewById(R.id.capital);
            flag = itemView.findViewById(R.id.flag);


        }
    }

}
