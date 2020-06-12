package com.example.bu;
import androidx.annotation.NonNull;
import androidx.core.app.ShareCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.CustomViewHolder> {
    private static ArrayList<User> mList;
    private static OnItemClick mCallback;
    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView name;
        protected TextView major;
        protected TextView phoneNum;

        public CustomViewHolder(View view) {
            super(view);
            this.name = (TextView) view.findViewById(R.id.counselor_name);
            this.major = (TextView) view.findViewById(R.id.counselor_major);
            this.phoneNum = (TextView) view.findViewById(R.id.counselor_phone);

            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        Log.d("adapterItemClick",mList.get(pos).getName());
                        mCallback.onCLick(mList.get(pos).getId());
                    }
                }
            });

        }
    }


    public MyAdapter(ArrayList<User> list, OnItemClick listener) {
        this.mList = list;
        this.mCallback = listener;
    }



    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.counselor_list, viewGroup, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }




    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {

        viewholder.name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        viewholder.major.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        viewholder.phoneNum.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);

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