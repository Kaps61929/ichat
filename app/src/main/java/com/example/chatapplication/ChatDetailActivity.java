package com.example.chatapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.chatapplication.Adapter.ChatAdaptor;
import com.example.chatapplication.Model.Messagemodel;
import com.example.chatapplication.databinding.ActivityChatDetailBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class ChatDetailActivity extends AppCompatActivity {
ActivityChatDetailBinding binding;
FirebaseAuth auth;
FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityChatDetailBinding.inflate(getLayoutInflater());
database=FirebaseDatabase.getInstance();
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        auth=FirebaseAuth.getInstance();
      final  String senderid=auth.getUid();
        String recieverid=getIntent().getStringExtra("userid");
        String username=getIntent().getStringExtra("username");
        String profilepic=getIntent().getStringExtra(" profilepic");


        binding.username.setText(username);
        Picasso.get().load(profilepic).placeholder(R.drawable.avtar).into(binding.profileImage);

binding.backarrow.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(ChatDetailActivity.this,MainActivity.class);
    startActivity(intent);
    }
});

        ArrayList<Messagemodel> messagemodels=new ArrayList<>();
        final String SenderRoom= senderid+recieverid;
        final String RecieverRoom=recieverid+senderid;
        final ChatAdaptor chatAdaptor=new ChatAdaptor(messagemodels,this);
        binding.chatrecylerview.setAdapter(chatAdaptor);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        binding.chatrecylerview.setLayoutManager(linearLayoutManager);
binding.send.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
       String message= binding.Message.getText().toString();
       final Messagemodel msgmodel=new Messagemodel(senderid,message);
       msgmodel.setTimestamp(new Date().getTime());
       binding.Message.setText("");

       database.getReference().child("Chats").child(SenderRoom).push().setValue(msgmodel).addOnSuccessListener(new OnSuccessListener<Void>() {
           @Override
           public void onSuccess(Void unused) {
               database.getReference().child("Chats").child(RecieverRoom).push().setValue(msgmodel).addOnSuccessListener(new OnSuccessListener<Void>() {
                   @Override
                   public void onSuccess(Void unused) {

                   }
               });
           }
       });
    }
});


// Read from the database

        database.getReference().child("Chats").child(SenderRoom).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                messagemodels.clear();
                for(DataSnapshot  snapshot: dataSnapshot.getChildren())
                {
                    Messagemodel model=snapshot.getValue(Messagemodel.class);
                    messagemodels.add(model);
                }
                chatAdaptor.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

    }
}