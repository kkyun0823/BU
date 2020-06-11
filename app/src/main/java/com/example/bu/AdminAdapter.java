package com.example.bu;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.AdminViewHolder> {

    private static ArrayList<User> mList;

    public static class AdminViewHolder extends RecyclerView.ViewHolder {
        protected TextView name;
        protected TextView major;
        protected TextView phoneNum;


        public AdminViewHolder(View view) {
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
                    }
                }
            });

        }
    }


    public AdminAdapter(ArrayList<User> list) {
        this.mList = list;
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