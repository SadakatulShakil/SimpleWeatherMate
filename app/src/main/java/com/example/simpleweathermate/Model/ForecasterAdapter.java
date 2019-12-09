package com.example.simpleweathermate.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simpleweathermate.R;

import java.util.ArrayList;

public class ForecasterAdapter extends RecyclerView.Adapter<ForecasterAdapter.forecasterHolder> {

    private Context context;
    private ArrayList<Forecaster> forecasterList;

    public ForecasterAdapter(Context context, ArrayList<Forecaster> forecasterList) {
        this.context = context;
        this.forecasterList = forecasterList;
    }

    @NonNull
    @Override
    public ForecasterAdapter.forecasterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.forcaster_view,parent,false);
        return new forecasterHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecasterAdapter.forecasterHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return forecasterList.size();
    }

    public class forecasterHolder extends RecyclerView.ViewHolder {

        TextView dateAndDayTv, lowTempTv, highTempTv;
        public forecasterHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
