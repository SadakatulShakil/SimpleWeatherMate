package com.futuresky.simpleweathermate;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.futuresky.simpleweathermate.ForeCasterApi.WeatherList;
import com.futuresky.simpleweathermate.Model.SimpleDateTimeDay;

public class ForeCasterDetailsActivity extends AppCompatActivity {

    private TextView lowTemp, highTemp, humidity, pressure, windSpeed, seaLevel, groundLevel, reviewTime, reviewDay, reviewDate;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fore_caster_details);

        inItView();
        Intent intent = getIntent();
        WeatherList weatherListInfo = (WeatherList) intent.getSerializableExtra("weatherInfo");

        reviewTime.setText(SimpleDateTimeDay.getTime(weatherListInfo.getDt()));
        reviewDay.setText(SimpleDateTimeDay.getDay(weatherListInfo.getDt()));
        reviewDate.setText(SimpleDateTimeDay.getDate(weatherListInfo.getDt()));

        lowTemp.setText(weatherListInfo.getMain().getTempMin() + "°C");
        highTemp.setText(weatherListInfo.getMain().getTempMax() + "°C");
        humidity.setText(weatherListInfo.getMain().getHumidity() + "%");
        pressure.setText(weatherListInfo.getMain().getPressure() + "psi");
        seaLevel.setText(weatherListInfo.getMain().getSeaLevel() + "cm");
        groundLevel.setText(weatherListInfo.getMain().getGrndLevel() + "cm");
        windSpeed.setText(weatherListInfo.getWind().getSpeed() + "k/h");
    }

    private void inItView() {
        lowTemp = findViewById(R.id.lowTempTV);
        highTemp = findViewById(R.id.highTempTV);
        humidity = findViewById(R.id.humidityTv);
        pressure = findViewById(R.id.pressureTv);
        windSpeed = findViewById(R.id.windSpeedTv);
        seaLevel = findViewById(R.id.seaLevelTv);
        groundLevel = findViewById(R.id.groundLevelTv);
        reviewTime = findViewById(R.id.reviewTime);
        reviewDay = findViewById(R.id.reviewDay);
        reviewDate = findViewById(R.id.reviewDate);
    }
}