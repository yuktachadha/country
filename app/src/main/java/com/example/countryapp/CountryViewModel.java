package com.example.countryapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CountryViewModel extends AndroidViewModel {

    private CountryRepository countryRepository;
    private LiveData<List<Country>> getAllCountry;

    public CountryViewModel(@NonNull Application application) {
        super(application);
        countryRepository = new CountryRepository(application);
        getAllCountry= countryRepository.getAll();
    }
    public void insert(List<Country> countryList)
    {
        countryRepository.insert(countryList);
    }
public void deleteAll(){countryRepository.deleteAll();}
    public LiveData<List<Country>> getAll() {
        return getAllCountry;
    }
}
