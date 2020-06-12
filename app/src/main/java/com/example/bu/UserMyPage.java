package com.example.bu;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserMyPage extends AppCompatActivity {
    private User user;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage_user);
        Intent i = getIntent();
        user = (User)i.getSerializableExtra("userinfo");
        Log.d("unmae",user.getName());
        TextView tname = (TextView)findViewById(R.id.for_name);
        tname.setText(user.getName().toString());
        TextView tbirth = (TextView)findViewById(R.id.for_birth);
        tbirth.setText(user.getBirth().toString());
        TextView tnum = (TextView)findViewById(R.id.for_num);
        tnum.setText(user.getPhoneNum().toString());
        Button pwupdate_btn = (Button)findViewById(R.id.modi_pw_btn);
        pwupdate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDBuilder = new AlertDialog.Builder(UserMyPage.this);
                alertDBuilder.setMessage("비밀번호를 바꾸시겠습니까?");
                alertDBuilder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText pwt = (EditText)findViewById(R.id.for_pw);
                        String pw = pwt.getText().toString();
                        user.setPassword(pw);
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("/user/" + user.getId());
                        myRef.setValue(user);
                        Toast.makeText(UserMyPage.this, "변경되었습니다.",Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
                alertDBuilder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(UserMyPage.this, "취소되었습니다.", Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog alertDialog = alertDBuilder.create();
                alertDialog.show();
            }
        });

    }
}
