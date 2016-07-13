package com.himan.recycledemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycle_view;
    private List<String> list_datas;
    private RecycleDemoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }


    public void initView(){
        recycle_view = (RecyclerView) findViewById(R.id.recycle_view);
    }


    public void initData(){
        list_datas = new ArrayList<>();
        for(int i = 0;i<100;i++){
            list_datas.add("data"+i);
        }
        adapter = new RecycleDemoAdapter(this, list_datas);
        recycle_view.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        recycle_view.setAdapter(adapter);
    }




}
