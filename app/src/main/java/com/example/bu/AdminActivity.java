package com.example.bu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {
    private ArrayList<User> userList = new ArrayList<User>();
    private AdminAdapter adapter;
    private DatabaseReference mRef;
    private DatabaseReference mRefUser;
    private FirebaseDatabase mDatabase;
    private RecyclerView recyclerView;
    private ArrayList<User> counselorList = new ArrayList<User>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("wait");
        mRefUser = mDatabase.getReference("user");
        readData(new FirebaseCallback() {
            @Override
            public void onCallback(ArrayList<User> list) {
                adapter = new AdminAdapter(list,AdminActivity.this);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);

            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.request_counselor);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        final TextView userCnt = (TextView)findViewById(R.id.user_cnt);
        final TextView counselorCnt = (TextView)findViewById(R.id.counselor_cnt);

        readUser(new FirebaseCallback() {
            @Override
            public void onCallback(ArrayList<User> list) {
                int userNum =0;
                int counselorNum = 0;
                for(User user : list){
                    if(user.getState()==0) userNum++;
                    else if(user.getState()==2) counselorNum++;
                }
                userCnt.setText(""+userNum);
                counselorCnt.setText(""+counselorNum);
            }
        });

    }
    private void readData(final FirebaseCallback firebaseCallback){

        ValueEventListener listener = new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    counselorList.add(data.getValue(User.class));
                }
                firebaseCallback.onCallback(counselorList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };

        mRef.addListenerForSingleValueEvent(listener);
    }

    private void readUser(final FirebaseCallback firebaseCallback){
        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data: dataSnapshot.getChildren()){
                    userList.add(data.getValue(User.class));
                }
                firebaseCallback.onCallback(userList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };


        mRefUser.addListenerForSingleValueEvent(userListener);

    }

    private interface FirebaseCallback{
        void onCallback(ArrayList<User> list);
    }


}
