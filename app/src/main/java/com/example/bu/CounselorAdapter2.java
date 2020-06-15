package com.example.bu;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class CounselorAdapter2 extends RecyclerView.Adapter<CounselorAdapter2.CounselorViewHolder> {
    private static ArrayList<Contents> mList;
    private static CounselorActivity activity;
    public static class CounselorViewHolder extends RecyclerView.ViewHolder {
        protected TextView title;
        protected TextView request_id;


        public CounselorViewHolder(View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.content_title);
            this.request_id = (TextView) view.findViewById(R.id.content_writer);


            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
//                        Log.d("adapterItemClick",mList.get(pos).getName());
                        activity.selectComplete(mList.get(pos).getRequest_id(), mList.get(pos).getTitle());

                    }
                }
            });

        }
    }


    public CounselorAdapter2(ArrayList<Contents> list, CounselorActivity counselorActivity) {
        this.mList = list;
        this.activity = counselorActivity;

    }



    @Override
    public CounselorViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.counsel_request_list, viewGroup, false);

        CounselorViewHolder viewHolder = new CounselorViewHolder(view);

        return viewHolder;
    }




    @Override
    public void onBindViewHolder(@NonNull CounselorViewHolder viewholder, int position) {

        viewholder.title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        viewholder.request_id.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);

        viewholder.title.setGravity(Gravity.CENTER);
        viewholder.request_id.setGravity(Gravity.CENTER);


        viewholder.title.setText(mList.get(position).getTitle());
        viewholder.request_id.setText(mList.get(position).getRequest_id());

    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

}