package com.futuresky.simpleweathermate;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.futuresky.simpleweathermate.ForeCasterApi.WeatherList;
import com.futuresky.simpleweathermate.Model.SimpleDateTimeDay;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ForeCasterDetailsActivity extends AppCompatActivity {

    private TextView lowTemp, highTemp, humidity, pressure, windSpeed, seaLevel, groundLevel, reviewTime, reviewDay, reviewDate;
    private ImageView previewImage;
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

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.US);

        try {
            Date estimatedTime12Am = sdf.parse("12:00 AM");
            Date get12AMTime = sdf.parse(reviewTime.getText().toString().trim());

            int diff = get12AMTime.compareTo(estimatedTime12Am);

            if(diff==0){///equal diff
                previewImage.setImageResource(R.drawable.twelve_am);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Date estimatedTime3Am = sdf.parse("03:00 AM");
            Date get3AMTime = sdf.parse(reviewTime.getText().toString().trim());

            int diff = get3AMTime.compareTo(estimatedTime3Am);

            if(diff==0){///equal diff
                previewImage.setImageResource(R.drawable.three_am);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Date estimatedTime6Am = sdf.parse("06:00 AM");
            Date get6AMTime = sdf.parse(reviewTime.getText().toString().trim());

            int diff = get6AMTime.compareTo(estimatedTime6Am);

            if(diff==0){///equal diff
                previewImage.setImageResource(R.drawable.six_am);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Date estimatedTime9Am = sdf.parse("09:00 AM");
            Date get9AMTime = sdf.parse(reviewTime.getText().toString().trim());

            int diff = get9AMTime.compareTo(estimatedTime9Am);

            if(diff==0){///equal diff
                previewImage.setImageResource(R.drawable.nine_am);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Date estimatedTime12Pm = sdf.parse("12:00 PM");
            Date get12PMTime = sdf.parse(reviewTime.getText().toString().trim());

            int diff = get12PMTime.compareTo(estimatedTime12Pm);

            if(diff==0){///equal diff
                previewImage.setImageResource(R.drawable.twelve_pm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Date estimatedTime3Pm = sdf.parse("03:00 PM");
            Date get3PMTime = sdf.parse(reviewTime.getText().toString().trim());

            int diff = get3PMTime.compareTo(estimatedTime3Pm);

            if(diff==0){///equal diff
                previewImage.setImageResource(R.drawable.three_pm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Date estimatedTime6Pm = sdf.parse("06:00 PM");
            Date get6PMTime = sdf.parse(reviewTime.getText().toString().trim());

            int diff = get6PMTime.compareTo(estimatedTime6Pm);

            if(diff==0){///equal diff
                previewImage.setImageResource(R.drawable.six_pm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Date estimatedTime9Pm = sdf.parse("09:00 PM");
            Date get9PMTime = sdf.parse(reviewTime.getText().toString().trim());

            int diff = get9PMTime.compareTo(estimatedTime9Pm);

            if(diff==0){///equal diff
            previewImage.setImageResource(R.drawable.nine_pm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        previewImage = findViewById(R.id.previewImage);
    }
}