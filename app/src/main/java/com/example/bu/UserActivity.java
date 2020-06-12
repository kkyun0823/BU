package com.example.bu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity implements OnItemClick {
    static final int GET_STRING =1;
    private ArrayList<User> userList = new ArrayList<User>();
    private MyAdapter adapter = new MyAdapter(userList, this);
    private DatabaseReference mRef;
    private FirebaseDatabase mDatabase;
    private RecyclerView recyclerView;
    private User user;
    private String dst_id;
    @Override
    public void onCLick(String id) {
        Intent intent = new Intent(getApplicationContext(), WriteActivity.class);
        dst_id = id;
        intent.putExtra("user", user);
        intent.putExtra("dst", dst_id);
        startActivityForResult(intent,1);
    }

    public void onActivityResult(int requsetCode, int resultCode, Intent data){
        if(requsetCode== GET_STRING){
            if(resultCode== RESULT_OK){

            }
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("user");
        readData(new FirebaseCallback() {
            @Override
            public void onCallback(ArrayList<User> list) {
                System.out.println(userList);
                recyclerView.setAdapter(adapter);            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.counselor_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        Intent intent =getIntent();
        user = (User)intent.getSerializableExtra("user");
        Log.d("uname",user.getName());


    }
    public void goNext(){

    }

    private void readData(final FirebaseCallback firebaseCallback){

        ValueEventListener listener = new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    if(data.getValue(User.class).getState()==2) {
                        userList.add(data.getValue(User.class));
                    }
                }
                firebaseCallback.onCallback(userList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        mRef.addListenerForSingleValueEvent(listener);
    }
    private interface FirebaseCallback{
        void onCallback(ArrayList<User> list);
    }

}
