package com.example.dsm2018.practice1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButton1Clicked(View v) {
        Toast.makeText(this, "버튼이 눌렸습니다.", Toast.LENGTH_LONG).show();
    }

    public void onButton2Clicked(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.naver.com"));
        startActivity(intent);
    }

    public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCf5dF74yRlb4Bn8hgMq6FrA?view_as=subscriber"));
        startActivity(intent);
    }
}
