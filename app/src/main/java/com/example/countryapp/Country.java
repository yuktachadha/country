package com.example.countryapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import androidx.room.TypeConverters;


import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;


@Entity(tableName = "country")
public class Country implements Serializable{

    @PrimaryKey(autoGenerate = true)
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @SerializedName("flag")
    @Expose
    private String flag;


    @SerializedName("capital")
    @Expose
    private String capital;

    @SerializedName("region")
    @Expose
    private String region;

    @SerializedName("subregion")
    @Expose
    private  String subregion;

    @SerializedName("population")
    @Expose
    private String population;


    @TypeConverters(LanguageConverter.class)
    private List<Language> languages;

    public int getId() {
        return id;
    }

    public String getFlag() { return flag; }
    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public String getRegion() {
        return region;
    }

    public String getSubregion() {
        return subregion;
    }

    public String getPopulation() {
        return population;
    }


    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public Country(String flag,String name, String capital, String region, String subregion, String population) {
        this.flag = flag;
        this.name = name;
        this.capital = capital;
        this.region = region;
        this.subregion = subregion;
        this.population = population;
    }

    public void setId(int id) {
        this.id = id;
    }
}

