package com.employee_api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.employee_api.Api.EmployeeAPI;
import com.employee_api.model.Employee;
import com.employee_api.model.EmployeeCUD;
import com.employee_api.url.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Update_Delete extends AppCompatActivity {
    private EditText etEmpNo;
    private EditText etEmpName, etEmpSalary,etEmpAge;
    private Button btnSearch, btnUpdate, btnDelete;
    Retrofit retrofit;
    EmployeeAPI employeeAPI;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__delete);



        btnDelete = findViewById(R.id.btndelete);
        btnSearch = findViewById(R.id.btnsea);
        btnUpdate = findViewById(R.id.btnupdate);
        etEmpNo = findViewById(R.id.etemp);
        etEmpName = findViewById(R.id.etempname);
        etEmpAge = findViewById(R.id.etempage);
        etEmpSalary = findViewById(R.id.etempsalary);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEmployee();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteEmployee();

            }
        });

    }

    private void loadData(){
        EmployeeAPI employeeAPI = URL.createInstance().create(EmployeeAPI.class);
        Call<Employee>listCall = employeeAPI.getEmployeeByID(Integer.parseInt(etEmpNo.getText().toString()));

        listCall.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                etEmpName.setText(response.body().getEmployee_name());
                etEmpSalary.setText(Float.toString(response.body().getEmployee_salary()));
                etEmpAge.setText(Integer.toString(response.body().getEmployee_age()));

            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Toast.makeText(Update_Delete.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void updateEmployee(){
        EmployeeAPI employeeAPI = URL.createInstance().create(EmployeeAPI.class);
        EmployeeCUD employee = new EmployeeCUD(
                etEmpName.getText().toString(),
                Float.parseFloat(etEmpSalary.getText().toString()),
                Integer.parseInt(etEmpAge.getText().toString())
        );

        Call<Employee> voidCall = employeeAPI.updateEmployee(Integer.parseInt(etEmpNo.getText().toString()), employee);

       voidCall.enqueue(new Callback<Employee>() {
           @Override
           public void onResponse(Call<Employee> call, Response<Employee> response) {
               Toast.makeText(Update_Delete.this, "Successfully updated", Toast.LENGTH_SHORT).show();
           }

           @Override
           public void onFailure(Call<Employee> call, Throwable t) {
               Toast.makeText(Update_Delete.this, "Error", Toast.LENGTH_SHORT).show();
           }
       });
    }

    private void deleteEmployee(){
        EmployeeAPI employeeAPI = URL.createInstance().create(EmployeeAPI.class);
        Call<Void> voidCall = employeeAPI.deleteEmployee(Integer.parseInt(etEmpNo.getText().toString()));

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(Update_Delete.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                Toast.makeText(Update_Delete.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });
    }

}
