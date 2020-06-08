package com.example.bu;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
    EditText id;
    User user;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        user = new User();
        id = (EditText)findViewById(R.id.idText);



        Button btn_agree = (Button)findViewById(R.id.btn_agree);
        btn_agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //회원가입 버튼 눌렀을 때
                EditText text_id = (EditText)findViewById(R.id.sign_id_text);
                user.setId(text_id.getText().toString());
                EditText text_pw = (EditText)findViewById(R.id.password);
                user.setPassword(text_pw.getText().toString());
                EditText text_ph = (EditText)findViewById(R.id.phone_num);
                user.setPhoneNum(text_ph.getText().toString());
                EditText text_na = (EditText)findViewById(R.id.name);
                user.setName(text_na.getText().toString());
                EditText text_bd = (EditText)findViewById(R.id.birth);
                user.setBirth(text_bd.getText().toString());
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("/user");
                myRef.setValue(user);
                Toast.makeText(getApplicationContext(),"회원가입 완료되었습니다.", Toast.LENGTH_LONG).show();
                setResult(RESULT_OK);
                finish();
            }
        });


        Button btn_cancel =(Button)findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "취소되었습니다.", Toast.LENGTH_LONG).show();
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }







}
