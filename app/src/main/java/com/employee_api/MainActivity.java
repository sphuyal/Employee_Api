package com.employee_api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.employee_api.Api.EmployeeAPI;
import com.employee_api.model.Employee;
import com.employee_api.url.URL;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView TvOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TvOutput = findViewById(R.id.TvOutput);


        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(URL.base_url)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();


        EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);
        Call<List<Employee>> listCall = employeeAPI.getAllEmployees();


        //Asynchronous call
        listCall.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {

                if (!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Error code" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Employee> employeeList = response.body();

                for (Employee emp: employeeList){
                    String data = "";
                    data += "id: " + emp.getId() + "\n";
                    data += "Name is :" + emp.getEmployee_name() + "\n";
                    data += "Salary :" + emp.getEmployee_salary() + "\n";
                    data += "Age is :" + emp.getEmployee_age() + "\n";
                    data += "Profile Image :" +emp.getProfile_image() + "\n";

                    TvOutput.append(data);


                }


            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Log.d("Msg","onFailure: " + t.getLocalizedMessage());
                Toast.makeText(MainActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
