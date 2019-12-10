package com.example.simpleweathermate.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simpleweathermate.ForeCasterApi.WeatherList;
import com.example.simpleweathermate.MainActivity;
import com.example.simpleweathermate.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.example.simpleweathermate.MainActivity.imageExtention;
import static com.example.simpleweathermate.MainActivity.imageUrl;

public class ForecasterAdapter extends RecyclerView.Adapter<ForecasterAdapter.forecasterHolder> {

    private Context context;
    private List<WeatherList> weatherLists;

    public ForecasterAdapter(Context context, List<WeatherList> weatherLists) {
        this.context = context;
        this.weatherLists = weatherLists;
    }

    @NonNull
    @Override
    public ForecasterAdapter.forecasterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.forcaster_view, parent, false);
        return new forecasterHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecasterAdapter.forecasterHolder holder, int position) {

        //Image
        /*String forecastIcon = weatherLists.get(position).getWeather().get(0).getIcon();
        String iconUrl = imageUrl + forecastIcon + imageExtention;
        Picasso.get().load(iconUrl).into(holder.imageView);*/
        loadIcon(holder,position);
        //Time
        String time = SimpleDateTimeDay.getTime(weatherLists.get(position).getDt());
        holder.timeTV.setText(time);

        //Day and Date
        String dayName = SimpleDateTimeDay.getDay(weatherLists.get(position).getDt());
        holder.dayTV.setText(dayName);
        /////////////////////////////////////////
        String dateDay = SimpleDateTimeDay.getDate(weatherLists.get(0).getDt());
        holder.dateTV.setText(dateDay);

        //MinTemp and MaxTemp
        String minTemp = weatherLists.get(position).getMain().getTempMin() + "°C";
        holder.lowTempTV.setText(minTemp);
        ///////////////////////////////////
        String maxTemp = weatherLists.get(position).getMain().getTempMax() + "°C";
        holder.highTempTV.setText(maxTemp);

    }

    private void loadIcon(ForecasterAdapter.forecasterHolder holder, int position) {
        String descriptionIcon = weatherLists.get(position).getWeather().get(0).getDescription();
        if(descriptionIcon.equals("clear sky")){
            holder.imageView.setImageResource(R.drawable._021_night_1);
        }
        else if(descriptionIcon.equals("few clouds")){
            holder.imageView.setImageResource(R.drawable._021_cloudy);
        }
        else if(descriptionIcon.equals("broken clouds")){
            holder.imageView.setImageResource(R.drawable._021_rain_2);
        }
        else if(descriptionIcon.equals("shower rain")){
            holder.imageView.setImageResource(R.drawable._021_rain_1);
        }
        else if(descriptionIcon.equals("rain")){
            holder.imageView.setImageResource(R.drawable._021_rain);
        }
        else if(descriptionIcon.equals("thunderstorm")){
            holder.imageView.setImageResource(R.drawable._021_storm);
        }
        else if(descriptionIcon.equals("snow")){
            holder.imageView.setImageResource(R.drawable._021_snowing_3);
        }
        else if(descriptionIcon.equals("haze")){
            holder.imageView.setImageResource(R.drawable._021_winter);
        }
    }

    @Override
    public int getItemCount() {
        return weatherLists.size();
    }

    public class forecasterHolder extends RecyclerView.ViewHolder {

        private TextView timeTV, dayTV, dateTV, lowTempTV, highTempTV;
        private ImageView imageView;

        public forecasterHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.rvWeatherIV);
            timeTV = itemView.findViewById(R.id.rvTimeTV);
            dayTV = itemView.findViewById(R.id.rvDayTV);
            dateTV = itemView.findViewById(R.id.rvDateTV);
            lowTempTV = itemView.findViewById(R.id.rvMinTempTV);
            highTempTV = itemView.findViewById(R.id.rvMaxTempTV);
        }
    }
}
