package com.example.bu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CompleteActivity extends AppCompatActivity {
    private Contents content;
    private User counselor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);

        Intent intent = getIntent();
        content = (Contents)intent.getSerializableExtra("content");
        counselor = (User)intent.getSerializableExtra("counselor");

        TextView title = (TextView)findViewById(R.id.read_title);
        title.setText(content.getTitle());
        TextView category = (TextView) findViewById(R.id.read_category);
        category.setText(content.getCategory());
        TextView maintext = (TextView) findViewById(R.id.read_contents);
        maintext.setText(content.getMaintext());
        TextView reply = (TextView)findViewById(R.id.select_reply);
        reply.setText(content.getReply());
    }
}
