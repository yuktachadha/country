package com.example.countryapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Country.class}, version = 2,exportSchema = false)
public abstract class CountryDatabase extends RoomDatabase {

    private static final String DATABASE_NAME ="CountryDatabase";

    private static volatile CountryDatabase instance;

    public abstract CountryDao countryDao();

    public static CountryDatabase getInstance(Context context) {

        if (instance == null) {
            synchronized (CountryDatabase.class){
                if (instance == null) {
 instance = Room.databaseBuilder(context, CountryDatabase.class, DATABASE_NAME)
         .addCallback(callback)
         .fallbackToDestructiveMigration()
         .build();
                }
            }

        }
        return instance;
    }

  static Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new PopulateAsyncTask(instance).execute();
        }
    };
    private static class PopulateAsyncTask extends AsyncTask<Void, Void, Void> {
        private CountryDao countryDao;
        PopulateAsyncTask(CountryDatabase countryDatabase) {
            countryDao = countryDatabase.countryDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            countryDao.deleteAll();
            return null;
        }
    }

}
