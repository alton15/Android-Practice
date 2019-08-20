package com.example.filesample;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    EditText edit_data;
    Button btn_save, btn_load, btn_next;
    ImageView image;
    String[] file_name = { "lion_king.jpg", "lion_king_2.png", "lion_king_3.jpg" };
    int[] file_id = { R.raw.lion_king, R.raw.lion_king_2, R.raw.lion_king_3 };
    int idx = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_next = findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idx = (idx + 1) % file_name.length;
                // raw 파일 접근을 위한 것
                // getResources() : res 폴더에 접근할 때 사용하는 함수
                // openRawResource(id값) : raw 폴더에 있는 파일을 접근할 때 사용하는 함수
                // -> inputStream 객체를 반환
                InputStream inputStream = getResources().openRawResource(file_id[idx]);
                // Bitmap : 그림파일을 로드한 결과를 저장하는 클래스
                // BitmapFactory : 다양한 방법으로 비트맵 객체를 생성하는 클래스
                // decodeStream() : 열려있는 inputStream 객체로 byte[] -> 이미지로 변환
                // decodeFile() : 저장되있는 파일로 그림을 로드할 때 사용하는 함수
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                // setImageBitmap : 비트맵 객체에 저장된 그림을 이미지뷰에 업로드할 때 사용하는 함수
                image.setImageBitmap(bitmap);
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(),
                        "그림파일 " + idx + " 출력", Toast.LENGTH_SHORT).show();
            }
        });

        image = findViewById(R.id.image);

        edit_data = findViewById(R.id.edit_data);

        btn_save = findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // edit_data에 작성된 문자열을 파일로 저장
                // (어떤 파일을 열것인가(켜져있는거 없애고 들어옴), )
                try {
                    // 파일을 쓰기모드 접근 : openFileOutput 함수
                    FileOutputStream fileOutputStream = openFileOutput("sample.txt", MODE_PRIVATE);
                    String data = edit_data.getText().toString();
                    // 데이터 쓰기 : write()
                    fileOutputStream.write(data.getBytes());    //문자열을 byte로 바꿔줌
                    Toast.makeText(getApplicationContext(),
                            "저장 완료", Toast.LENGTH_SHORT).show();
                    // 파일 저장 및 접근 해제 : close()
                    fileOutputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btn_load = findViewById(R.id.btn_load);
        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 파일 불러오기
                try {
                    FileInputStream fileInputStream = openFileInput("sample.txt");

                    // 읽어들일 파일의 사이즈 만큼의 바이트 배열을 생성
                    byte[] data = new byte[fileInputStream.available()];
                    // 파일 읽기
                    fileInputStream.read(data);
                    // byte[] -> String 변환 및 결과 출력력
                    String result = new String(data);
                    edit_data.setText(result);
                    // 파일 접근 종료
                    fileInputStream.close();
               } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    // 파일이 존재하지 않을 때 뜨는 토스트
                    Toast.makeText(getApplicationContext(),
                            "파일을 찾을 수 없음", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
