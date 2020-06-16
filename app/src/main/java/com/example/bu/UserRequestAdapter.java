package com.example.bu;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class UserRequestAdapter extends RecyclerView.Adapter<UserRequestAdapter.URViewHolder> {
    private static ArrayList<Contents> mList;
    private static OnitemClick2 mCallback;

    public static class URViewHolder extends RecyclerView.ViewHolder{
        protected TextView title;
        protected TextView counselor_name;

        public URViewHolder(View view){
            super(view);
            this.title = (TextView)view.findViewById(R.id.text_title);
            this.counselor_name = (TextView)view.findViewById(R.id.text_counselor);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos!=RecyclerView.NO_POSITION){
                        mCallback.onClick2(mList.get(pos).getTitle());
                    }
                }
            });
        }

    }
    public UserRequestAdapter(ArrayList<Contents> list, OnitemClick2 listener){
        this.mList = list;
        this.mCallback = listener;

    }

    public URViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_request_list, viewGroup, false);
        URViewHolder viewHolder = new URViewHolder(view);
        return viewHolder;
    }
    public void onBindViewHolder(@NonNull URViewHolder viewholder, int position){
        viewholder.title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        viewholder.counselor_name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);

        viewholder.title.setGravity(Gravity.CENTER);
        viewholder.counselor_name.setGravity(Gravity.CENTER);

        viewholder.title.setText(mList.get(position).getTitle());
        viewholder.counselor_name.setText(mList.get(position).getDst_name());

    }
    public int getItemCount(){
        return(null!= mList ? mList.size() : 0);
    }
}
