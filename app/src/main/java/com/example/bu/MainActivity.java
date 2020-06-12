package com.example.bu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
        //alert dialog 출력 후 확인 누르면 회원가입 페이지로 넘어가는 구조
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setMessage("상담사 가입을 희망하시는 경우 상담사 가입에 체크해주시길 바랍니다. 심사 후 상담사 등록을 도와드립니다.");
        alertBuilder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        AlertDialog dialog = alertBuilder.create();
        dialog.show();
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
            if(users.getId().equals(id.getText().toString()) && users.getPassword().equals(password.getText().toString())){
                user = users;
                break;
            }
        }
        //0 일반유저, 1 대기상태, 2 승인상태, 3 관리자
        if(user != null && user.getState() == 0){
            Toast.makeText(getApplicationContext(),"로그인 성공",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), UserActivity.class);
            intent.putExtra("user",user);
            startActivityForResult(intent,1);
        }else if(user != null && user.getState() == 2){
            Toast.makeText(getApplicationContext(),"상담사 로그인",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), CounselorActivity.class);
            intent.putExtra("counselor",user);
            startActivityForResult(intent,1);
        }
        else if (user != null && user.getState() == 3){
            Toast.makeText(getApplicationContext(),"관리자 로그인",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
            startActivityForResult(intent,1);

        }
        else{
            Toast.makeText(getApplicationContext(),"id와 password를 확인해주세요",Toast.LENGTH_LONG).show();
            password.setText("");
            return;
        }

    }
}
