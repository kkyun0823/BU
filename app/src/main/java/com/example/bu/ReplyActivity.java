package com.example.bu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReplyActivity extends AppCompatActivity {
    private Contents content;
    private User counselor;
    private FirebaseDatabase database;
    private DatabaseReference mRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);

        Intent intent = getIntent();
        content = (Contents)intent.getSerializableExtra("content");
        counselor = (User)intent.getSerializableExtra("counselor");

        TextView title = (TextView)findViewById(R.id.read_title);
        title.setText(content.getTitle());
        TextView category = (TextView) findViewById(R.id.read_category);
        category.setText(content.getCategory());
        TextView maintext = (TextView) findViewById(R.id.read_contents);
        maintext.setText(content.getMaintext());

    }

    public void onClickSend(View view){
        EditText editText = (EditText) findViewById(R.id.write_reply);
        String reply = editText.getText().toString();
        content.setReply(reply);
        Log.v("reply",reply);
        Log.v("content.reply",content.getReply());
        database = FirebaseDatabase.getInstance();
        mRef = database.getReference("/contents/"+counselor.getId()+"/"+content.getTitle());
        mRef.setValue(content);
        DatabaseReference mRef2 = database.getReference("/contents/"+content.getRequest_id()+"/"+content.getTitle());
        mRef2.setValue(content);
        Toast.makeText(getApplicationContext(),"작성되었습니다.", Toast.LENGTH_LONG).show();
        setResult(RESULT_OK);
        finish();
    }

    public void onClickCancel(View view){
        Toast.makeText(getApplicationContext(),"취소되었습니다.",Toast.LENGTH_LONG).show();
        setResult(RESULT_CANCELED);
        finish();
    }
}
