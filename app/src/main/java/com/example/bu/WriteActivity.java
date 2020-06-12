package com.example.bu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WriteActivity extends AppCompatActivity {
    private String cate;
    private Contents contents;
    private User user;
    private String dst_id;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        contents = new Contents();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendwrite);
        Intent intent = getIntent();
        user =(User)intent.getSerializableExtra("user");
        dst_id = (String)intent.getSerializableExtra("dst");
        Log.d("uname",user.getName());
        Log.d("dstid", dst_id);
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cate = (String)parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Button send_btn = (Button)findViewById(R.id.send_btn);
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //전송 버튼 눌렀을 떄
                EditText text_title = (EditText)findViewById(R.id.title_edittext);
                contents.setTitle(text_title.getText().toString());
                EditText text_main = (EditText)findViewById(R.id.main_edittext);
                contents.setMaintext(text_main.getText().toString());
                contents.setCategory(cate);
                contents.setRequest_id(user.getId());
                contents.setDst_id(dst_id);

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                if(text_title!=null&&text_main!=null){
                    DatabaseReference myref = database.getReference("/contents/"+dst_id.toString()+"/"+text_title.getText().toString());
                    myref.setValue(contents);
                }
                Toast.makeText(getApplicationContext(),"작성되었습니다.", Toast.LENGTH_LONG).show();
                setResult(RESULT_OK);
                finish();
            }
        });
        Button can_btn = (Button)findViewById(R.id.cancel_write);
        can_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"취소되었습니다.",Toast.LENGTH_LONG).show();
                setResult(RESULT_CANCELED);
                finish();
            }
        });

    }
}
