package com.example.bu;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WriteActivity extends AppCompatActivity {
    private String cate;
    private Contents contents;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        contents = new Contents();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendwrite);

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

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                if(text_title!=null&&text_main!=null){
                    DatabaseReference myref = database.getReference("/contents/");

                }else{

                }
            }
        });

    }
}
