package com.example.chatapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapplication.Model.Messagemodel;
import com.example.chatapplication.R;
import com.example.chatapplication.databinding.SampleRecieverBinding;
import com.example.chatapplication.databinding.SampleSenderBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ChatAdaptor extends  RecyclerView.Adapter {
ArrayList<Messagemodel> messagemodels;
Context context;

    public ChatAdaptor(ArrayList<Messagemodel> messagemodels, Context context) {
        this.messagemodels = messagemodels;
        this.context = context;
    }

    public int Sendertype=1,Recievertype=2;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == Sendertype) {
            View view = LayoutInflater.from(context).inflate(R.layout.sample_sender,parent,false);
            return  new SenderViewholder(view);
        }
        else{
            View view = LayoutInflater.from(context).inflate(R.layout.sample_reciever,parent,false);
            return  new RecieverViewholder(view);
        }


    }

    @Override
    public void onBindViewHolder(@NonNull  RecyclerView.ViewHolder holder, int position) {

        if(holder.getClass()==SenderViewholder.class){
            ( (SenderViewholder)holder).sendermsg.setText(messagemodels.get(position).getMessage());

        }
        else{
            ( (RecieverViewholder)holder).recievermsg.setText(messagemodels.get(position).getMessage());
        }


    }

    @Override
    public int getItemCount() {
        return messagemodels.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(messagemodels.get(position).getuId().equals(FirebaseAuth.getInstance().getUid())){
            return Sendertype;
        }
       else{
           return Recievertype;
        }
    }

    public class SenderViewholder extends RecyclerView.ViewHolder {
        TextView sendermsg,sendertime;
        public SenderViewholder(@NonNull View itemView) {
            super(itemView);
            sendermsg= itemView.findViewById(R.id.sendermsg);
            sendertime=itemView.findViewById(R.id.sendertime);

        }
    }
    public class RecieverViewholder extends RecyclerView.ViewHolder {
        TextView recievermsg,recievertime;
        public RecieverViewholder(@NonNull View itemView) {
            super(itemView);
            recievermsg= itemView.findViewById(R.id.recievermsg);
            recievertime=itemView.findViewById(R.id.recievertime);
        }
    }
}
