package com.example.countryapp.Network;

import retrofit2.Call;
import retrofit2.http.GET;

import com.example.countryapp.Country;

import java.util.List;

public interface Api {
    @GET("rest/v2/region/asia")
    Call<List<Country>> getAll();
}
