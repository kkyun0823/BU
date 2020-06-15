package com.example.bu;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
        Button btn_ok = findViewById(R.id.btn_detail_ok);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ShowDetail.this, "이전화면으로 돌아갑니다.", Toast.LENGTH_LONG).show();
                finish();
            }
        });
        Button btn_del = findViewById(R.id.btn_detail_del);
        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ShowDetail.this);
                builder.setMessage("삭제하신 글은 복구가 불가능합니다. 글을 삭제하시겠습니까?");
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mRef = mDatabase.getReference("contents/"+user.getId()+"/"+content.getTitle());
                        mRef.removeValue();
                        mRef = mDatabase.getReference("contents/"+content.getDst_id()+"/"+content.getTitle());
                        mRef.removeValue();
                        Toast.makeText(ShowDetail.this, "삭제되었습니다.",Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ShowDetail.this, "취소되없습니다.",Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }
}
