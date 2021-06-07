package com.example.countryapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CountryRepository {

    private CountryDatabase database;

    private LiveData<List<Country>> getAllCountry;

    public CountryRepository(Application application)
    {
        database = CountryDatabase.getInstance(application);
        getAllCountry =  database.countryDao().getAll();
    }

    public void insert(List<Country> countryList){
        new InsertAsyncTask(database).execute(countryList);
    }

    public void deleteAll(){
        new DeleteAsyncTask(database).execute();
    }

    public LiveData<List<Country>> getAll()
    {
        return getAllCountry;
    }

    static class InsertAsyncTask extends AsyncTask<List<Country>, Void, Void> {
        private CountryDao countryDao;
        InsertAsyncTask(CountryDatabase countryDatabase)
        {
            countryDao = countryDatabase.countryDao();
        }
        @Override
        protected Void doInBackground(List<Country>... countries) {
            countryDao.insert(countries[0]);
            return null;
        }
    }
    static class DeleteAsyncTask extends AsyncTask<List<Country>, Void, Void> {
        private CountryDao countryDao;
        DeleteAsyncTask(CountryDatabase countryDatabase)
        {
            countryDao = countryDatabase.countryDao();
        }
        @Override
        protected Void doInBackground(List<Country>... countries) {
            countryDao.deleteAll();
            return null;
        }
    }

}
