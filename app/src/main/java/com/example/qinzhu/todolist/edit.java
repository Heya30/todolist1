package com.example.qinzhu.todolist;

import android.app.AlarmManager;
import android.app.Service;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

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

    Button alarm = findViewById(R.id.alarm);

    alarm.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

                //获取系统当前时间

                Calendar calendar=Calendar.getInstance();

                int hourNow=calendar.get(Calendar.HOUR_OF_DAY);

                int minuteNow=calendar.get(Calendar.MINUTE);

                //弹出时间对话框

                TimePickerDialog timePickerDialog=new TimePickerDialog(edit.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override

                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        //c为改了之后的时间

                        Calendar c=Calendar.getInstance();
                        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        c.set(Calendar.MINUTE,minute);
                        //bean1.setTime(c);

                    }

                },hourNow,minuteNow,true);



                timePickerDialog.show();

            }


    });



    }

}
