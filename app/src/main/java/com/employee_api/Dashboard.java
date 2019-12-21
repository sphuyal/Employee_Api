package com.employee_api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Dashboard extends AppCompatActivity {

    Button btnShowEmployee, btnRegisterEmployee, btnSearchEmployee, btnUpdateDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        btnShowEmployee = findViewById(R.id.btnshowemployee);
        btnRegisterEmployee = findViewById(R.id.btnregisteremployee);
        btnSearchEmployee = findViewById(R.id.btnsearchemployee);
        btnUpdateDelete = findViewById(R.id.btnupdatedeleteemployee);


        btnRegisterEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, Register.class);
                startActivity(intent);
            }
        });

        btnSearchEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, Search.class);
                startActivity(intent);
            }
        });

        btnUpdateDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, Update_Delete.class);
                startActivity(intent);
            }
        });


    }
}
