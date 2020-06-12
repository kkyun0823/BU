package com.example.bu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CounselorActivity extends AppCompatActivity {
    private ArrayList<Contents> contentsList = new ArrayList<Contents>();
    private CounselorAdapter adapter;
    private DatabaseReference mRef;
    private FirebaseDatabase mDatabase;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counselor);
        Intent intent = getIntent();
        User counselor = (User)intent.getSerializableExtra("counselor");
        Log.d("CID",counselor.getName());

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("contents/"+counselor.getId());
        readData(new FirebaseCallback() {
            @Override
            public void onCallback(ArrayList<Contents> list) {
                adapter = new CounselorAdapter(list);
                recyclerView.setAdapter(adapter);

            }
        });
        recyclerView = (RecyclerView)findViewById(R.id.counsel_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    private void readData(final FirebaseCallback firebaseCallback){

        ValueEventListener listener = new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.getChildren()){
     
                    contentsList.add(data.getValue(Contents.class));
                }
                firebaseCallback.onCallback(contentsList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };

        mRef.addListenerForSingleValueEvent(listener);
    }

    private interface FirebaseCallback{
        void onCallback(ArrayList<Contents> list);
    }
}
