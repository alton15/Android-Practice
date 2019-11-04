package com.project.user_management;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    EditText edt_id, edt_password, edt_name, edt_age;
    Button btn_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edt_id = findViewById(R.id.edt_id);
        edt_password = findViewById(R.id.edt_password);
        edt_name = findViewById(R.id.edt_name);
        edt_age = findViewById(R.id.edt_age);

        btn_register = findViewById(R.id.tv_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
