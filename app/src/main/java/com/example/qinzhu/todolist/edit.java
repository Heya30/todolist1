package com.example.qinzhu.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class edit extends AppCompatActivity {


    private EditText ed_todo;
    private EditText ed_remark;
    private String todo;
    private String remark;
    private bean bean1;
    private Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        ed_todo = findViewById(R.id.thingsToDo);

        ed_remark = findViewById(R.id.remark);

        spinner = findViewById(R.id.selectLevel);

//        spinner.setOnItemClickListener(new AdapterView.OnItemSelectedListener(){
//
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        Button save = findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                todo = ed_todo.getText().toString();

                remark = ed_remark.getText().toString();

                bean1 = new bean(todo,remark);
                Intent intent = new Intent ();
                intent.putExtra("data",bean1);
                setResult(RESULT_OK,intent);
                finish();

            }
        });


    }

}
