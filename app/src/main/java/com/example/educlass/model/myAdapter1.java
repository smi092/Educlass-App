package com.example.educlass.model;
import android.content.Context;
import android.graphics.ColorSpace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.educlass.R;

import java.util.ArrayList;
import java.util.List;
public class myAdapter1 extends RecyclerView.Adapter<myAdapter1.MyViewHolder> {
    ArrayList<model1> mList;
    Context context;
    public myAdapter1(Context context,ArrayList<model1> mList){
        this.mList=mList;
        this.context=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.itemyoutube,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        model1 model= mList.get(position);
        holder.ytlink.setText(model.getYtlink());
        holder.Class.setText(model.getclass());
        holder.subject.setText(model.getSubject());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView ytlink,Class,subject;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            ytlink=itemView.findViewById(R.id.link_text);
            Class=itemView.findViewById(R.id.class_text);
            subject=itemView.findViewById(R.id.subject_text);

        }
    }


}
