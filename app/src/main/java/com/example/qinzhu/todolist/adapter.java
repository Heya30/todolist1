package com.example.qinzhu.todolist;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public  class adapter extends RecyclerView.Adapter<adapter.MyViewHolder> {



    private final Context context;

    private ArrayList<bean> datas;

    public adapter(Context context, ArrayList<bean> datas) {

        this.context = context;

        this.datas = datas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemview = View.inflate(context, R.layout.item_recyclerview, null);

        final MyViewHolder holder = new MyViewHolder(itemview);

        holder.todoView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                int position = holder.getAdapterPosition();
                bean bean_click = datas.get(position);

                if(AlterClick != null) {
                AlterClick.OnAlterClick(bean_click,position);}
            }
        });


        return  holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        //根据位置得到对应的数据

        String thedata = datas.get(i).getTodo();

        myViewHolder.todo.setText(thedata);


    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {


        private TextView todo;

        View todoView;


        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            todo = itemView.findViewById(R.id.id_todo);

            todoView = itemView;

        }

    }


    public void addData(bean bean1) {


        datas.add(0, bean1);
        //刷新适配器
        notifyItemInserted(0);

    }

    public void removeData(int position) {

        datas.remove(position);

        notifyItemRemoved(position);

    }

    public static interface AlterClickListener {

        public void OnAlterClick(bean bean_init,int position);

    }

    AlterClickListener AlterClick;

    public void setAlterClickListener(AlterClickListener AlterClick) {

        this.AlterClick = AlterClick;
    }




}
