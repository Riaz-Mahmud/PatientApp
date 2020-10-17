package com.example.patientapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Chart extends AppCompatActivity {
    Button buttonCall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        buttonCall = findViewById(R.id.btncall);
        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:01941550320"));
                startActivity(intent);

            }
        });
        buttonCall = findViewById(R.id.btncall2);
        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:01941550320"));
                startActivity(intent);

            }
        });
        buttonCall = findViewById(R.id.btncall3);
        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:01941550320"));
                startActivity(intent);

            }
        });

        /*listView=(ListView)findViewById(R.id.listview);
        ArrayList<String> arrayList=new ArrayList<>();

        arrayList.add("Hospital No.1");
        arrayList.add("hospital no.2");

        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);*/
    }
}