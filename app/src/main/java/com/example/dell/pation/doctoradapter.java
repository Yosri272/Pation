package com.example.dell.pation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;



public class doctoradapter extends RecyclerView.Adapter<doctoradapter.MyViewHolder> {

    private List<doctor> doctorlist = new ArrayList<>();
    private Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name,hview,d_address,d_balans,d_mo;
        public ImageView img;
        String yosri;
public Button booking;
        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.textView);
            hview = (TextView) view.findViewById(R.id.textView2);
            d_mo = (TextView) view.findViewById(R.id.textView3);
            d_balans = (TextView) view.findViewById(R.id.textView4);
            d_address = (TextView) view.findViewById(R.id.textView5);
            img = (ImageView) view.findViewById(R.id.imageView);
        //    booking=(Button) view.findViewById(R.id.booked);

        }
    }


    public doctoradapter(List<doctor> doctorlist) {
        this.doctorlist = doctorlist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_find, parent, false);
        context=itemView.getContext();
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        final doctor doctor = doctorlist.get(position);
        holder.name.setText(doctor.getName());
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, doctor.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.hview.setText(doctor.getSpe());
        holder.d_balans.setText(doctor.getd_balans());
        holder.d_address.setText(doctor.getd_address());
        holder.d_mo.setText(doctor.getd_mo());

        Glide.with(context).load("http://192.168.43.34/hassan/yosri/img.php?d_name="+doctor.getName()).into(holder.img);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, doctor.getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return doctorlist.size();
    }
}
