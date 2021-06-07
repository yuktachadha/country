package com.example.countryapp;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countryapp.Network.Api;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class MainActivity extends AppCompatActivity {

    private CountryViewModel countryViewModel;
    private static final String URL_DATA="https://restcountries.eu/";
    private RecyclerView recyclerView;
    private CountryRepository countryRepository;
   private CountryAdapter countryAdapter;
    private List<Country> countryList;

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ImageView image = (ImageView)findViewById(R.id.flag);
//        SvgLoader.pluck()
//                .with(this)
//                .setPlaceHolder(R.mipmap.ic_launcher,R.mipmap.ic_launcher)
//                .load("https://restcountries.eu/data/afg.svg",image);
//        @Override protected void onDestroy(){
//            super.onDestroy();
//            SvgLoader.pluck().close();
//        }



       recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
       recyclerView.setItemAnimator(new DefaultItemAnimator());

       countryRepository=new CountryRepository(getApplication());

        countryList = new ArrayList<>();

        countryAdapter = new CountryAdapter(this,countryList);

        countryViewModel = new ViewModelProvider(this).get(CountryViewModel.class);

        countryViewModel.getAll().observe(this, new Observer<List<Country>>() {
            @Override
            public void onChanged(List<Country> countryList) {
                countryAdapter.getAll(countryList);
            recyclerView.setAdapter(countryAdapter);
            Log.d("main","onchange"+countryList);
            }
        });
        networkRequest();

    }
    public  boolean onCreateOnMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.clear_data){
            Toast.makeText(this,"clear",Toast.LENGTH_SHORT).show();
            countryViewModel.deleteAll();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void networkRequest() {
        Retrofit retrofit=new Retrofit.Builder()
        .baseUrl(URL_DATA)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
         Api api = retrofit.create(Api.class);
        Call <List<Country>> call=api.getAll();

        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                if(response.isSuccessful()){
                countryRepository.insert(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }



}