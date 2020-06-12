package com.example.bu;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.AdminViewHolder> {

    private static ArrayList<User> mList;
    private static Activity activity;
    public static class AdminViewHolder extends RecyclerView.ViewHolder {
        protected TextView name;
        protected TextView major;
        protected TextView phoneNum;


        public AdminViewHolder(View view) {
            super(view);
            this.name = (TextView) view.findViewById(R.id.counselor_name);
            this.major = (TextView) view.findViewById(R.id.counselor_major);
            this.phoneNum = (TextView) view.findViewById(R.id.counselor_phone);
            final Context context = view.getContext();
            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    final int pos = getAdapterPosition();
                    final User user = mList.get(pos);
                    if(pos != RecyclerView.NO_POSITION){

                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                        builder.setTitle("상담가 승인 요청").setMessage(user.getName() + "\n 승인 / 거절");
                        builder.setPositiveButton("승인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
                                DatabaseReference mRef = mDatabase.getReference("user/"+user.getId());
                                user.setState(2);
                                mRef.setValue(user);
                                mRef = mDatabase.getReference("wait/"+user.getId());
                                mRef.removeValue();

                                Toast.makeText(activity.getApplicationContext(), "승인되었습니다.", Toast.LENGTH_SHORT).show();
                                activity.recreate();
                            }
                        });
                        builder.setNegativeButton("거절", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
                                DatabaseReference mRef = mDatabase.getReference("wait/"+user.getId());
                                mRef.removeValue();
                                Toast.makeText(activity.getApplicationContext(), "거절되었습니다.", Toast.LENGTH_SHORT).show();
                                activity.recreate();
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                    }

                }
            });

        }
    }


    public AdminAdapter(ArrayList<User> list, AdminActivity adminActivity) {
        this.mList = list;
        this.activity = adminActivity;
    }



    @Override
    public AdminViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.counselor_list, viewGroup, false);

        AdminViewHolder viewHolder = new AdminViewHolder(view);

        return viewHolder;
    }




    @Override
    public void onBindViewHolder(@NonNull AdminViewHolder viewholder, int position) {

        viewholder.name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.major.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.phoneNum.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);

        viewholder.name.setGravity(Gravity.CENTER);
        viewholder.major.setGravity(Gravity.CENTER);
        viewholder.phoneNum.setGravity(Gravity.CENTER);



        viewholder.name.setText(mList.get(position).getName());
        viewholder.major.setText(mList.get(position).getMajor());
        viewholder.phoneNum.setText(mList.get(position).getPhoneNum());
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

}