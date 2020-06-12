package com.example.bu;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
    private EditText id;
    private User user;
    private int state;
    private String major;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        user = new User();
        id = (EditText)findViewById(R.id.idText);


        Spinner spinner = (Spinner)findViewById(R.id.majorspinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                major = (String)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
                user.setState(state);
                user.setMajor(major);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                if(state == 0) {
                    DatabaseReference myRef = database.getReference("/user/" + text_id.getText().toString());
                    myRef.setValue(user);
                }else if(state == 1){
                    DatabaseReference myRef = database.getReference("/wait/" + text_id.getText().toString());
                    myRef.setValue(user);
                }
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

    public void onCheckBoxClicked(View view){
        boolean checked = ((CheckBox)view).isChecked();
        Spinner sp = (Spinner) findViewById(R.id.majorspinner);
        TextView tx = (TextView)findViewById(R.id.major_text);

        if(checked){
            state = 1;
            sp.setVisibility(View.VISIBLE);
            tx.setVisibility(View.VISIBLE);
        }
        else {
            state = 0;
            sp.setVisibility(View.GONE);
            tx.setVisibility(View.GONE);
        }

    }



}
