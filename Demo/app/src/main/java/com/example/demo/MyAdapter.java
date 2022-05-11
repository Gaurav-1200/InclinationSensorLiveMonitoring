package com.example.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<DataDetails> list;

    public MyAdapter(Context context, ArrayList<DataDetails> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataDetails request=list.get(position);
        holder.Time.setText(request.getTime());
        holder.Voltage.setText(request.getVoltage());
        holder.Angle.setText(request.getAngle());
        holder.Strain.setText(request.getStrain());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Time,Voltage, Angle, Strain;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Time=itemView.findViewById(R.id.time);
            Voltage=itemView.findViewById(R.id.vol);
            Angle=itemView.findViewById(R.id.ang);
            Strain=itemView.findViewById(R.id.str);
        }
    }
}
