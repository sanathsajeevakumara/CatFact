package com.sanath.catfat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import static com.sanath.catfat.MainActivity.FIRST_NAME;
import static com.sanath.catfat.MainActivity.LAST_NAME;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        String firstName = intent.getStringExtra(FIRST_NAME);
        String lastName = intent.getStringExtra(LAST_NAME);

        TextView first = findViewById(R.id.name1);
        TextView last = findViewById(R.id.name2);

        first.setText("Full Name : "+firstName+ " ");
        last.setText(lastName);
    }
}
