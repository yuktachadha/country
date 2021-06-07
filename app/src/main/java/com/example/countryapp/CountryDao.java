package com.example.countryapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import androidx.room.OnConflictStrategy;

@Dao
public interface CountryDao {

    // below method is use to
    // add data to database.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Country> countryList);

    // on below line we are making query to
    // delete all courses from our database.
    @Query("DELETE FROM country")
    void deleteAll();

    // below line is to read all the courses from our database.
    // in this we are ordering our courses in ascending order
    // with our course name.
    @Query("SELECT * FROM country")
    LiveData<List<Country>> getAll();
}
