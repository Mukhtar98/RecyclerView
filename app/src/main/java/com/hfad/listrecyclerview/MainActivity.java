package com.hfad.listrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    ArrayList<Model> list;
    MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        populateList();
        myAdapter = new MyAdapter(this, list);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }

    private void populateList() {
        list = new ArrayList<>();
        list.add(new Model("Section1 ", R.drawable.photo));
        list.add(new Model("Section2", R.drawable.photo2));
        list.add(new Model("Section3", R.drawable.photo3));
        list.add(new Model("Section4", R.drawable.photo4));
        list.add(new Model("Section5", R.drawable.photo));
        list.add(new Model("Section6", R.drawable.photo2));
        list.add(new Model("Section7", R.drawable.photo3));
        list.add(new Model("Section8", R.drawable.photo4));
        list.add(new Model("Section9", R.drawable.photo));
        list.add(new Model("Section10", R.drawable.photo2));
        list.add(new Model("Section11", R.drawable.photo3));
        list.add(new Model("Section12", R.drawable.photo4));
        list.add(new Model("Section13", R.drawable.photo));
        list.add(new Model("Section14", R.drawable.photo2));
        list.add(new Model("Section15", R.drawable.photo3));
        list.add(new Model("Section16", R.drawable.photo4));
        list.add(new Model("Section17", R.drawable.photo));
        list.add(new Model("Section18", R.drawable.photo2));
        list.add(new Model("Section19", R.drawable.photo3));
        list.add(new Model("Section20", R.drawable.photo4));

    }
}