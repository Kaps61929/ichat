package com.example.chatapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapplication.ChatDetailActivity;
import com.example.chatapplication.Model.User;
import com.example.chatapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class usersadapter extends RecyclerView.Adapter<usersadapter.ViewHolder> {
ArrayList<User> list;
Context context;

    public usersadapter(ArrayList<User> list, Context context) {
        this.list=list;
        this.context=context;
    }

    @NonNull

    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_showuser,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  usersadapter.ViewHolder holder, int position) {
User users=list.get(position);
        Picasso.get().load(users.getProfilepic()).placeholder(R.drawable.avtar).into(holder.image);
        holder.username.setText(users.getUsername());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ChatDetailActivity.class);
                intent.putExtra("userid",users.getUserId());
                intent.putExtra("username",users.getUsername());
                intent.putExtra("profilepic",users.getProfilepic());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
ImageView image;
TextView username,lastmessage;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            image=itemView.findViewById(R.id.profile_image);
            username =itemView.findViewById(R.id. username);
            lastmessage=itemView.findViewById(R.id.lastmessage);
        }
    }
}
