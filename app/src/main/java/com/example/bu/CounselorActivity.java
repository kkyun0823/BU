package com.example.bu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CounselorActivity extends AppCompatActivity {
    private ArrayList<Contents> contentsList = new ArrayList<Contents>();
    private ArrayList<Contents> completeList = new ArrayList<Contents>();
    private CounselorAdapter adapter;
    private DatabaseReference mRef;
    private DatabaseReference mRef2;
    private FirebaseDatabase mDatabase;
    private RecyclerView recyclerView;
    private User counselor;
    private RecyclerView recyclerView2;
    private CounselorAdapter2 adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counselor);
        Intent intent = getIntent();
        counselor = (User)intent.getSerializableExtra("counselor");

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("contents/"+counselor.getId());
        mRef2 = mDatabase.getReference("contents/"+counselor.getId());
        readData(new FirebaseCallback() {
            @Override
            public void onCallback(ArrayList<Contents> list) {
                adapter = new CounselorAdapter(list,CounselorActivity.this);
                recyclerView.setAdapter(adapter);

            }

        });
        recyclerView = (RecyclerView)findViewById(R.id.counsel_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        readData2(new FirebaseCallback() {
            @Override
            public void onCallback(ArrayList<Contents> list) {
                adapter2 = new CounselorAdapter2(list,CounselorActivity.this);
                recyclerView2.setAdapter(adapter2);

            }

        });
        recyclerView2 = (RecyclerView)findViewById(R.id.complete_list);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        recyclerView2.setLayoutManager(linearLayoutManager1);

    }

    private void readData(final FirebaseCallback firebaseCallback){

        ValueEventListener listener = new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    if(data.getValue(Contents.class).getReply() == null) {
                        contentsList.add(data.getValue(Contents.class));
                    }
                }
                firebaseCallback.onCallback(contentsList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };

        mRef.addListenerForSingleValueEvent(listener);
    }

    private void readData2(final FirebaseCallback firebaseCallback){

        ValueEventListener listener2 = new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    if(data.getValue(Contents.class).getReply() != null) {
                        completeList.add(data.getValue(Contents.class));
                    }
                }
                firebaseCallback.onCallback(completeList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };

        mRef2.addListenerForSingleValueEvent(listener2);
    }

    private interface FirebaseCallback{
        void onCallback(ArrayList<Contents> list);

    }

    public void selectContent(String request_id, String title){
        Log.v("test",request_id+"//////////"+title+"/////"+counselor.getName());
        Contents contents = null;
        for(Contents content : contentsList){
            if(content.getRequest_id().equals(request_id) && content.getTitle().equals(title)) contents = content;
        }
        Intent intent = new Intent(getApplicationContext(), ReplyActivity.class);
        intent.putExtra("content",contents);
        intent.putExtra("counselor",counselor);
        startActivityForResult(intent,1);
    }

    public void selectComplete(String request_id, String title){
        Log.v("test",request_id+"//////////"+title+"/////"+counselor.getName());
        Contents contents = null;
        for(Contents content : completeList){
            if(content.getRequest_id().equals(request_id) && content.getTitle().equals(title)) contents = content;
        }
        Intent intent = new Intent(getApplicationContext(), CompleteActivity.class);
        intent.putExtra("content",contents);
        intent.putExtra("counselor",counselor);
        startActivityForResult(intent,1);
    }

}
