
package com.hfad.listrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import static android.text.TextUtils.isEmpty;

public class ItemsAdd extends AppCompatActivity {

    private EditText first_name;
    private EditText second_name;
    private AppCompatButton addItem;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_add);

        String firstname = getIntent().getStringExtra("first_name");
        String secondname = getIntent().getStringExtra("second_name");
        int image = getIntent().getIntExtra("image", -1);
        int position = getIntent().getIntExtra("position", -1);


        first_name = findViewById(R.id.first_name);
        second_name = findViewById(R.id.second_name);
        addItem = findViewById(R.id.addItems);

        first_name.setText(firstname);
        second_name.setText(secondname);


        addItem.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(first_name.getText().toString())||TextUtils.isEmpty(second_name.getText().toString())) {
                    Toast.makeText(ItemsAdd.this, "Empty field not allowed!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent returnIntent = new Intent();

                    returnIntent.putExtra("name", first_name.getText().toString());
                    returnIntent.putExtra("surname", second_name.getText().toString());
                    returnIntent.putExtra("image", image);
                    returnIntent.putExtra("position", position);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();

            }
        });

    }
}