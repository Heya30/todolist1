package com.example.qinzhu.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class alter extends AppCompatActivity {

    private EditText ed_todo;
    private EditText ed_remark;
    private String todo;
    private String remark;
    private bean bean1;
    private bean beanInit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter);

        ed_todo = findViewById(R.id.thingsToDo);

        ed_remark = findViewById(R.id.remark);

        Intent intent = getIntent();

        beanInit = (bean)intent.getSerializableExtra("init");

        ed_todo.setText(beanInit.getTodo());

        ed_remark.setText(beanInit.getRemark());

        Button save = findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                todo = ed_todo.getText().toString();

                remark = ed_remark.getText().toString();

                bean1 = new bean(todo,remark);
                Intent intent = new Intent ();
                intent.putExtra("update",bean1);
                setResult(RESULT_OK,intent);
                finish();

            }
        });


    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent();
        intent.putExtra("update",beanInit);
        setResult(RESULT_OK,intent);
        finish();
    }

}