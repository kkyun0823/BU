package com.example.bu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShowDetail extends AppCompatActivity {
    private Contents content;
    private String t_title;
    private DatabaseReference mRef;
    private FirebaseDatabase mDatabase;
    private User user;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdetail);
        Intent intent = getIntent();
        t_title = intent.getSerializableExtra("title").toString();
        user = (User)intent.getSerializableExtra("user");
        content = (Contents)intent.getSerializableExtra("content");
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("contents/"+user.getId());
        Log.d("OUT", user.getName());
        Log.d("OUT", t_title);
        Log.d("Title", content.getTitle());

        TextView title = (TextView)findViewById(R.id.detail_title);
        title.setText(content.getTitle());
        TextView category = (TextView)findViewById(R.id.detail_category);
        category.setText(content.getCategory());
        TextView main = (TextView)findViewById(R.id.detail_main);
        main.setText(content.getMaintext());
        TextView reply = (TextView)findViewById(R.id.detail_reply);
        if(content.getReply()==null) {
            reply.setText("답변이 등록되지 않았습니다.");
        }else{
            reply.setText(content.getReply());
        }

    }
}
