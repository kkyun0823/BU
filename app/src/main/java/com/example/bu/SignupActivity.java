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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SignupActivity extends AppCompatActivity {
    private EditText id;
    private User user;
    private int state;
    private String major;
    private ArrayList<User> userList = new ArrayList<User>();
    private Boolean checkId = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        user = new User();
        id = (EditText)findViewById(R.id.idText);
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mRef = mDatabase.getReference("user");
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    userList.add(data.getValue(User.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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
                EditText text_pw = (EditText)findViewById(R.id.password);
                EditText text_ph = (EditText)findViewById(R.id.phone_num);
                EditText text_na = (EditText)findViewById(R.id.name);
                EditText text_bd = (EditText)findViewById(R.id.birth);
                EditText text_cpw = (EditText)findViewById(R.id.password_chk);
                if(text_pw.getText().toString().equals("") || text_ph.getText().toString().equals("") || text_na.getText().toString().equals("") || text_bd.toString().equals("")){
                    Toast.makeText(getApplicationContext(),"정보를 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!checkId){
                    Toast.makeText(getApplicationContext(),"ID 중복 확인을 해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!text_pw.getText().toString().equals(text_cpw.getText().toString())){
                    Toast.makeText(getApplicationContext(),"비밀번호와 비밀번호 확인이 다릅니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                user.setId(text_id.getText().toString());
                user.setPassword(text_pw.getText().toString());
                user.setPhoneNum(text_ph.getText().toString());
                user.setName(text_na.getText().toString());
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


        Button duplicateCheckBtn = (Button)findViewById(R.id.duplicate_check_btn);
        duplicateCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText inputId = (EditText) findViewById(R.id.sign_id_text);
                String id = inputId.getText().toString();
                if(id.equals("") || id == null) {
                    Toast.makeText(getApplicationContext(),"아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                for(User users : userList){
                    if(id.equals(users.getId())) {
                        Toast.makeText(getApplicationContext(), "중복된 아이디입니다.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                Toast.makeText(getApplicationContext(), "사용 가능한 아이디입니다.", Toast.LENGTH_LONG).show();
                checkId = true;
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
