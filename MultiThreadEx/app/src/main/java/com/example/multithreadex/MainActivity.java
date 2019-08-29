package com.example.multithreadex;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    TextView textview;
    Button thread_start, down_img;
    ImageView img;
    EditText edt_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = findViewById(R.id.Img);
        down_img = findViewById(R.id.down_img);
        edt_url = findViewById(R.id.edt_url);

        thread_start = findViewById(R.id.thread_start);
        textview = findViewById(R.id.textview);

        down_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Download_thread thread = new Download_thread(edt_url.getText().toString());
                thread.start();
            }
        });

        thread_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sample = new Thread_Sample();
                sample.start();
            }
        });
    }
    Thread_Sample sample;

    // 다른 화면을 보고 있을 때 서브 스레드 일시정지
    @Override
    protected void onPause() {
        super.onPause();
        if(sample != null)
            sample.isRun = false;
    }

    // 다시 화면으로 돌아왔을때 서브 스레드 재시작
    @Override
    protected void onResume() {
        super.onResume();
    }

    // 핸들러 정의
    // 핸들러 : 서브 스레드가 연산한 결과값을 UI스레드에게 전달하기 위한 통로.
    // 핸들러에 있는 handleMessage 메소드를 오버라이딩해서 UI갱신 처리
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // 서브 스레드들의 데이터 구분
            if(msg.what == 1) { //이미지 스레드가 전송한 데이터
                Bitmap bitmap = (Bitmap)msg.obj;
                img.setImageBitmap(bitmap);
            } else {
                // Message 매개변수 : 데이터를 가지고 있는 객체
                Bundle bundle = msg.getData();
                int i = bundle.getInt("idx");
                textview.setText("서브스레드가 준 값 :" + i);
            }

        }
    };

    // 사용자가 입력한 url로 이미지를 다운받는 서브스레드
    class Download_thread extends Thread {
        String url;
        public Download_thread(String url) {
            this.url = url;
        }

        @Override
        public void run() {
            // 웹 서버 접속 및 요청
            try {
                URL url = new URL(this.url);

                // 요청한 결과(이미지)를 다운로드
                URLConnection connection = url.openConnection();
                connection.connect();
                // 다운 받은 데이터를 Bitmap 객체로 변환
                BufferedInputStream bufferedInputStream = new BufferedInputStream(connection.getInputStream());
                Bitmap bitmap = BitmapFactory.decodeStream(bufferedInputStream);
                // 결과를 핸들러로 전달
                Message message = handler.obtainMessage(1);
                message.obj = bitmap;
                message.sendToTarget();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 서브 스레드 동작용 클래스
    // 안드로이드에서 서브 스레드는 5개 이후부터는 동작이 늦춰져서 보인다
    class Thread_Sample extends Thread {
        boolean isRun = true;
        // 동작 시킬 코드를 run메소드에 정의
        @Override
        public void run() {
            // 서버에게 데이터 요청, 음악 실행, 연산 많은 작업
            // 서브스레드가 UI스레드의 위젯을 직접 접근하는 것이 막혀있다 -> UI스레드 핸들러 객체를 통한 데이터 전달을 해야함
            for(int i = 0; i < 1000 && isRun; i++) {
                // UI스레드에게 데이터 전송
                // Handler 객체를 통한 Message 객체 생성
                Message message = handler.obtainMessage();
                //전달할 데이터를 Bundle 객체 저장 및 Message 객체에 저장
                Bundle bundle = new Bundle();
                bundle.putInt("idx", i);
                message.setData(bundle);
                // 메시지 전송
                message.sendToTarget();
                try {
                    // 일정 시간동안 스레드가 일시정지하는 함수
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
