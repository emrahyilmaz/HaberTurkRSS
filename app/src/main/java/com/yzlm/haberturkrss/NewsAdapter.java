package com.yzlm.haberturkrss;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by yzlm on 14.06.2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder>  {
    ArrayList<News> dataset;
    Context context;
    public NewsAdapter(ArrayList<News> dataset, Context context) {
        this.dataset = dataset;
        this.context = context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_item, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,final int position) {
        holder.tvTitle.setText(dataset.get(position).getTitle());
        holder.tvDescription.setText(dataset.get(position).getDesc());
        holder.tvDate.setText(dataset.get(position).getPubdate());
        //Uri.parse(dataset.get(position).getLink())
        holder.ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = dataset.get(position).getLink().toString();

                Intent intent = new Intent(v.getContext(),NewsDetail.class);
                intent.putExtra("link",value);
                v.getContext().startActivity(intent);
            }
        });

        Picasso.with(context).load(dataset.get(position).getImage()).into(holder.ivImage);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvDescription;
        ImageView ivImage;
        TextView tvDate;
        TextView tvHour;

        public MyViewHolder(View v) {
            super(v);

            tvDescription = (TextView) v.findViewById(R.id.tvDescription);
            tvTitle = (TextView) v.findViewById(R.id.tvTitle);
            ivImage = (ImageView) v.findViewById(R.id.ivImage);
            tvDate=(TextView)v.findViewById(R.id.tvDate);
        }
    }
}
