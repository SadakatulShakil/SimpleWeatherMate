package com.futuresky.simpleweathermate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.futuresky.simpleweathermate.ForeCasterApi.WeatherList;
import com.futuresky.simpleweathermate.ForeCasterApi.WeatherResponseList;
import com.futuresky.simpleweathermate.Model.Api;
import com.futuresky.simpleweathermate.Model.ForecasterAdapter;
import com.futuresky.simpleweathermate.Model.RetrofitClient;
import com.futuresky.simpleweathermate.WeatherApi.WeatherResponse;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private TextView cityTV, countryTV, tempTV, statusTV, minTempTV, maxTempTV;
    private ImageView iconView;
    public String units = "metric";
    public String appID = "5541dc989e3b299629e61ba79a66829e";
    public static String imageUrl = "https://openweathermap.org/img/wn/";
    public static String imageExtention = ".png";
    private String imageIcon;
    private FusedLocationProviderClient client;
    Geocoder geocoder;
    private String latitude, longtitude;
    private RecyclerView recyclerView;
    private ForecasterAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        requestPermission();

        geocoder = new Geocoder(MainActivity.this, Locale.getDefault());

        client = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        client.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if (location != null) {
                    //txtLocation.setText(location.toString());
                    latitude = String.valueOf(location.getLatitude());
                    longtitude = String.valueOf(location.getLongitude());

                    //Toast.makeText(MainActivity.this, "Latitude: "+latitude +"Longitude: "+longtitude ,Toast.LENGTH_LONG).show();
                    loadingWeatherData();
                    loadingForecastWeather();
                } else {

                    Toast.makeText(MainActivity.this, "Error Here!", Toast.LENGTH_SHORT).show();


                }

            }
        });

    }


    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }


    }


    /* private void loadDataByLatLng() {
         Api apiService = RetrofitClient.getRetrofitInstance().create(Api.class);
         Call<WeatherResponseList> weatherResponseCall = apiService.getByLatLng(latitude,longtitude ,units,appID);
         weatherResponseCall.enqueue(new Callback<WeatherResponseList>() {
             @Override
             public void onResponse(Call<WeatherResponseList> call, Response<WeatherResponseList> response) {
                 if (response.code() == 200){
                     WeatherResponseList wr = response.body();
                     Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_LONG).show();
                 }else {
                     Toast.makeText(MainActivity.this, "errorCode-" + response.code(), Toast.LENGTH_LONG).show();
                 }
             }

             @Override
             public void onFailure(Call<WeatherResponseList> call, Throwable t) {

                 Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
             }
         });

     }*/

    private void loadingForecastWeather() {

        Retrofit retrofit = RetrofitClient.getRetrofitInstance();

        Api api = retrofit.create(Api.class);

        Call<WeatherResponseList> call = api.getWeatherForeCaster(latitude, longtitude, units, appID);

        call.enqueue(new Callback<WeatherResponseList>() {
            @Override
            public void onResponse(Call<WeatherResponseList> call, Response<WeatherResponseList> response) {

                if (response.code() == 200) {
                    WeatherResponseList wrf = response.body();
                    List<WeatherList> weatherLists = wrf.getList();

                    adapter = new ForecasterAdapter(MainActivity.this, weatherLists);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                    recyclerView.setLayoutManager(layoutManager);

                    if (adapter != null) {
                        recyclerView.setAdapter(adapter);
                    }

                } else {
                    Toast.makeText(MainActivity.this, "errorCode-" + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<WeatherResponseList> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                //Log.d("Main", "onFailure:  "+t.getLocalizedMessage());
            }
        });

    }

    private void loadingWeatherData() {

        Retrofit retrofit = RetrofitClient.getRetrofitInstance();

        Api api = retrofit.create(Api.class);

        Call<WeatherResponse> call = api.getByLatLng(latitude, longtitude, units, appID);

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {

                if (response.code() == 200) {
                    WeatherResponse wr = response.body();
                    String description = wr.getWeather().get(0).getDescription();
                    cityTV.setText(wr.getName());
                    countryTV.setText(wr.getSys().getCountry());
                    tempTV.setText(wr.getMain().getTemp() + "°C");
                    minTempTV.setText(wr.getMain().getTempMin() + "°C");
                    maxTempTV.setText(wr.getMain().getTempMax() + "°C");
                    statusTV.setText(description);
                    /*imageIcon = imageUrl + wr.getWeather().get(0).getIcon() + imageExtention;
                    Picasso.get().load(imageIcon).into(iconView);*/
                    customImage(description);
                } else {
                    Toast.makeText(MainActivity.this, "errorCode-" + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                //Log.d("Main", "onFailure:  "+t.getLocalizedMessage());
            }
        });
    }

    private void customImage(String weatherStatus) {
        Log.d("image", "customImage: ");
        if(weatherStatus.equals("clear sky")){
            iconView.setImageResource(R.drawable._021_night_1);
        }
        else if(weatherStatus.equals("few clouds")){
            iconView.setImageResource(R.drawable._021_cloudy);
        }
        else if(weatherStatus.equals("broken clouds")){
            iconView.setImageResource(R.drawable._021_rain_2);
        }
        else if(weatherStatus.equals("shower rain")){
            iconView.setImageResource(R.drawable._021_rain_1);
        }
        else if(weatherStatus.equals("rain")){
            iconView.setImageResource(R.drawable._021_rain);
        }
        else if(weatherStatus.equals("thunderstorm")){
            iconView.setImageResource(R.drawable._021_storm);
        }
        else if(weatherStatus.equals("snow")){
            iconView.setImageResource(R.drawable._021_snowing_3);
        }
        else if(weatherStatus.equals("haze")){
            iconView.setImageResource(R.drawable._021_winter);
        }

        else if(weatherStatus.equals("mist")){
            iconView.setImageResource(R.drawable._021_winter);
        }


    }

    private void initView() {

        cityTV = findViewById(R.id.cityTV);
        countryTV = findViewById(R.id.countryTV);
        tempTV = findViewById(R.id.temparetureTV);
        minTempTV = findViewById(R.id.minTempTV);
        maxTempTV = findViewById(R.id.maxTempTV);
        statusTV = findViewById(R.id.situationTv);
        iconView = findViewById(R.id.sun);
        recyclerView = findViewById(R.id.recycleViewOfForecaster);
    }

}
