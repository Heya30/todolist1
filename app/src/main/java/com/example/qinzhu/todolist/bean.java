package com.example.qinzhu.todolist;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.Calendar;

public class bean extends DataSupport implements Serializable {
    private int num ;
    private String todo;
    private String remark;
    private int level;
    private Calendar time;

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public bean( String todo,String remark) {
        //this.num = num;
        this.todo = todo;
        this.remark = remark;
        //this.level = level;
    }
    public bean(int position,String todo) {
        this.num =position;
        this.todo = todo;
        //this.remark = remark;
        //this.level = level;
    }

    public int getNum() {
        return num;
    }

    public String getTodo() {
        return todo;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
