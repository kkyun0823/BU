package com.example.bu;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bu.R;
import com.example.bu.User;

import java.util.ArrayList;
import java.util.Dictionary;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.CustomViewHolder> {

    private ArrayList<User> mList;

    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView name;
        protected TextView age;
        protected TextView phoneNum;


        public CustomViewHolder(View view) {
            super(view);
            this.name = (TextView) view.findViewById(R.id.counselor_name);
            this.age = (TextView) view.findViewById(R.id.counselor_age);
            this.phoneNum = (TextView) view.findViewById(R.id.counselor_phone);
        }
    }


    public MyAdapter(ArrayList<User> list) {
        this.mList = list;
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

        viewholder.name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.age.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.phoneNum.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);

        viewholder.name.setGravity(Gravity.CENTER);
        viewholder.age.setGravity(Gravity.CENTER);
        viewholder.phoneNum.setGravity(Gravity.CENTER);



        viewholder.name.setText(mList.get(position).getName());
        viewholder.age.setText(mList.get(position).getBirth());
        viewholder.phoneNum.setText(mList.get(position).getPhoneNum());
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

}