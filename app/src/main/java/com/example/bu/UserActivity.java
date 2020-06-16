package com.example.bu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    private String dst_name;
    @Override
    public void onCLick(String id, String name) {
        Intent intent = new Intent(getApplicationContext(), WriteActivity.class);
        dst_id = id;
        dst_name = name;
        intent.putExtra("user", user);
        intent.putExtra("dst", dst_id);
        intent.putExtra("dstname", dst_name);
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
        Button my_pagebtn = (Button) findViewById(R.id.mypage_btn);
        my_pagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(),UserMyPage.class);
                intent2.putExtra("userinfo", user);
                startActivity(intent2);
            }
        });



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

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        return true;
    }

}
