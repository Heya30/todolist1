package com.example.qinzhu.todolist;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import org.litepal.LitePal;
import org.litepal.LitePalApplication;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;

import static android.widget.Toast.*;

public class MainActivity  extends AppCompatActivity {

    private Intent intent;
    private Intent intentAlter;
    private adapter adapter;
    private RecyclerView recyclerview;
    private ArrayList<bean> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerview = findViewById(R.id.recyclerview);

        datas = new ArrayList<bean>();

        LitePal.getDatabase();

        datas = (ArrayList<bean>) DataSupport.findAll(bean.class);
        adapter = new adapter(MainActivity.this, datas);

        recyclerview.setAdapter(adapter);

        recyclerview.setLayoutManager(new LinearLayoutManager(MainActivity.this));

//        recyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        adapter.AlterClickListener onItemActionClick = new adapter.AlterClickListener() {

            @Override
            public void OnAlterClick(bean bean_init, int position) {

                bean bean = bean_init;
                intentAlter = new Intent(MainActivity.this, alter.class);
                intentAlter.putExtra("init", bean);
                startActivityForResult(intentAlter, 2);
                adapter.removeData(position);
                DataSupport.deleteAll(bean.class, "todo = ?", bean.getTodo());


            }


        };

        adapter.setAlterClickListener(onItemActionClick);

        initTouch();


    }

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {


            case R.id.back:

                makeText(this, "back", LENGTH_LONG).show();
                break;

            case R.id.about:

                makeText(this, "about", LENGTH_LONG).show();
                break;

            default:
        }
        return true;

    }


    public void add(View view) {

        intent = new Intent(MainActivity.this, edit.class);
        startActivityForResult(intent, 1);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        switch (requestCode) {

            case 1:
                if (resultCode == RESULT_OK) {
                    bean bean1 = (bean) data.getSerializableExtra("data");
                    adapter.addData(bean1);
                    bean1.setLevel(1);
                    bean1.setNum(1);
                    bean1.save();


                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {

                    bean beanNew = (bean) data.getSerializableExtra("update");
                    adapter.addData(beanNew);
                    beanNew.setLevel(1);
                    beanNew.setNum(1);
                    beanNew.save();

                }

            default:
        }

    }

    private void initTouch() {
        ItemTouchHelperCallback itemTouchHelperCallback = new ItemTouchHelperCallback();
        itemTouchHelperCallback.setOnitemTouchCallBackListener(onitemTouchCallBackListener);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchHelperCallback);
        itemTouchHelper.attachToRecyclerView(recyclerview);
    }

    private ItemTouchHelperCallback.OnitemTouchCallBackListener onitemTouchCallBackListener = new ItemTouchHelperCallback.OnitemTouchCallBackListener(){


        @Override
        public void onSwiped(int position) {

            if(datas != null){

                bean beanGet = adapter.getItem(position);

                DataSupport.deleteAll(bean.class, "todo = ?", beanGet.getTodo());

                adapter.removeData(position);
            }

        }

        @Override
        public boolean onMove(int startPosition, int endPosition) {

            adapter.notifyItemMoved(startPosition,endPosition);
            datas.subList(startPosition,endPosition);


            return true;
        }


    };


}