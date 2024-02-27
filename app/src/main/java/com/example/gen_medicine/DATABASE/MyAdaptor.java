package com.example.gen_medicine.DATABASE;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gen_medicine.R;
import com.example.gen_medicine.USER.ConsultFragment;

import java.util.ArrayList;

public class MyAdaptor extends RecyclerView.Adapter<MyAdaptor.MyViewHolder> {
    Context context;
    ArrayList<DocList> doc;

    public MyAdaptor(Context context, ArrayList<DocList> doc) {
        this.doc = doc;
        this.context=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.doc_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DocList dc=doc.get(position);
        holder.dname.setText(dc.dname);
        holder.hospital.setText(dc.hospital);
    }

    @Override
    public int getItemCount() {
        return doc.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView dname;
        TextView hospital;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            dname=itemView.findViewById(R.id.dname);
            hospital=itemView.findViewById(R.id.hospital);
        }
    }
    public void setFilter(ArrayList<DocList> l)
    {
        doc=l;
        notifyDataSetChanged();
    }
}
