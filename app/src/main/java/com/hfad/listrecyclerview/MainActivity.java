package com.hfad.listrecyclerview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {
    private static final int LAUNCH_NEXT_ACTIVITY = 2;

    RecyclerView recyclerView;
    ArrayList<Model> list;
    MyAdapter myAdapter;

    AppCompatImageView addButton;

    TextView selectAll, deleteAll;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButton = findViewById(R.id.itemAdd);


        ImageView Send = (ImageView) findViewById(R.id.itemAdd);
        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ItemsAdd.class);
                intent.putExtra("image", R.drawable.ic_baseline_add);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        selectAll = findViewById(R.id.selectAll);
        deleteAll = findViewById(R.id.deleteAll);

        populateList();
        myAdapter = new MyAdapter(this, list);
        recyclerView.setAdapter(myAdapter);
        myAdapter.setItemClickListener(this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ItemsAdd.class);
                startActivityForResult(intent, LAUNCH_NEXT_ACTIVITY);


            }
        });

        selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAll.setVisibility(View.VISIBLE);
                selectAll.setVisibility(View.GONE);
                myAdapter.setCheckBoxVisible(true);
            }
        });

        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAll.setVisibility(View.GONE);
                selectAll.setVisibility(View.VISIBLE);
                myAdapter.setCheckBoxVisible(false);
                myAdapter.deleteAll();
            }
        });

    }


    private void populateList() {
        list = new ArrayList<>();
        list.add(new Model("Section1", "Section5", R.drawable.photo));
        list.add(new Model("Section2", "Section6", R.drawable.photo2));
        list.add(new Model("Section3", "Section7", R.drawable.photo3));
        list.add(new Model("Section4", "Section8", R.drawable.photo4));

    }

    @Override
    public void onItemClick(int position, Model model) {
        Intent intent = new Intent(this, ItemsAdd.class);
        intent.putExtra("first_name", model.getName());
        intent.putExtra("second_name", model.getSurname());
        intent.putExtra("image", model.getImage());
        intent.putExtra("position", position);
        startActivityForResult(intent, LAUNCH_NEXT_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LAUNCH_NEXT_ACTIVITY) {
            if (resultCode == Activity.RESULT_OK) {
                String second_name = data.getStringExtra("surname");
                String first_name = data.getStringExtra("name");
                int image = data.getIntExtra("image", -1);
                int position = data.getIntExtra("position", -1);
                if (position != -1) {
                    Model model = new Model(first_name, second_name, image);
                    myAdapter.updateItem(position, model);
                } else {
                    Model model = new Model(first_name, second_name, R.drawable.photo);
                    myAdapter.insertItem(model);
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
            }

        }
    }
}