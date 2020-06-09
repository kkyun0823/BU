package com.example.bu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static final int GET_STRING = 1;
    EditText text;
    private List<User> userList = new ArrayList<User>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (EditText) findViewById(R.id.idText);

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

    }

    public void clickedSignup(View view){
        Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
        startActivityForResult(intent, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == GET_STRING){
            if(resultCode == RESULT_OK){

            }
        }
    }

    public void onClickedLogin(View view){
        System.out.println("btn call");
        User user = null;
        EditText id = (EditText)findViewById(R.id.idText);
        EditText password = (EditText)findViewById(R.id.passwordText);

        if(id.getText().toString().equals("") || password.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"id와 password를 입력해주세요",Toast.LENGTH_LONG).show();
            password.setText("");
            return;
        }
        for(User users : userList){
            System.out.println(users.getId());
            if(users.getId().equals(id.getText().toString()) && users.getPassword().equals(password.getText().toString())){
                user = users;
                System.out.println("fff");
                break;
            }
        }
        if(user != null){
            Toast.makeText(getApplicationContext(),"로그인 성공",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), CounselorListActivity.class);
            startActivityForResult(intent,1);
        }else{
            Toast.makeText(getApplicationContext(),"id와 password를 확인해주세요",Toast.LENGTH_LONG).show();
            password.setText("");
            return;
        }

    }
}
