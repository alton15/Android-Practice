package com.example.sqlitesample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText edt_date, edt_title, edt_content;
    Button btn_insert, btn_delete, btn_select, btn_update;
    MemojangDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_date = findViewById(R.id.edt_date);
        edt_title = findViewById(R.id.edt_title);
        edt_content = findViewById(R.id.edt_content);

        btn_insert = findViewById(R.id.btn_insert);
        btn_delete = findViewById(R.id.btn_delete);
        btn_select = findViewById(R.id.btn_select);
        btn_update = findViewById(R.id.btn_update);

        // DB 접근을 위한 MemojangDB 객체 생성
        db = new MemojangDB(getApplicationContext(), "MEMO.db",null,1);

        // '추가' 버튼 클릭
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.insert(edt_date.getText().toString(),
                        edt_title.getText().toString(),
                        edt_content.getText().toString());
                Toast.makeText(getApplicationContext(), "데이터 추가 완료", Toast.LENGTH_SHORT).show();
            }
        });

        // '조회' 버튼 클릭
        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = db.select();
                TextView tv = findViewById(R.id.select_result);
                tv.setText(result);
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.delete(edt_title.getText().toString());
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.update(edt_title.getText().toString(), edt_content.getText().toString());
            }
        });
    }
}
