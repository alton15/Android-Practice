package com.example.menusample;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //버튼 저장 변수
    Button btn_1, btn_dialog, btn_dialog_2;       // xml의 어떤 위젯을 넣어줄지, 클릭 시 어떤 이벤트 발생할지 넣어줘야함
    View dialogView;
    LinearLayout linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_dialog = findViewById(R.id.btn_dialog);
        btn_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // dialog를 띄울 때 사용하는 클래스 변수 생성
                // (어디에 dialog를 띄울 것인가)
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("제목을 입력하는 공간");  // setPositive는 확인 setNegative는 취소 setView는 xml 띄우기
                dialog.setIcon(R.mipmap.ic_launcher);
                // (이름, 어떤 이벤트 발생할 것인지)
                dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        float x_size = btn_dialog.getScaleX();
                        btn_dialog.setScaleX(x_size * 2);
                    }
                });
                dialog.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        float x_size = btn_dialog.getScaleX();
                        btn_dialog.setScaleX(x_size / 2);
                    }
                });
                // 만들어진 다이얼로그를 화면에 띄우기
                dialog.show();
            }
        });

        btn_dialog_2 = findViewById(R.id.btn_dialog_2);
        btn_dialog_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // xml파일을 로드 및 다이얼로그로 설정
                // (어디에 로드할지, 뭘 로드할지)
                dialogView = View.inflate(MainActivity.this,
                        R.layout.dialog_sample, null);
                AlertDialog.Builder adl = new AlertDialog.Builder(MainActivity.this);
                // 로드된 화면을 설정
                adl.setView(dialogView);
                adl.setTitle("학생과 팀 이름 입력");
                adl.setPositiveButton("등록", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText edt_name, edt_team;
                        // 다이얼로그 화면에 있는 editText를 추출
                        edt_name = dialogView.findViewById(R.id.edt_name);
                        edt_team = dialogView.findViewById(R.id.edt_team);
                        btn_1.setText(edt_name.getText());
                        btn_dialog.setText(edt_team.getText());
                    }
                });
                adl.show();
            }
        });

        linear = findViewById(R.id.linear);
        // 이건 팝업 메뉴
        btn_1 = findViewById(R.id.btn_1);
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 버튼 클릭시 팝업 메뉴 발생
                // (얘가 누군지, 어떤 화면에서 처리할 것인지)
                final PopupMenu popupMenu = new PopupMenu(getApplicationContext(), v);
                // 메뉴 파일을 불러오기
                getMenuInflater().inflate(R.menu.menu_popup, popupMenu.getMenu());  //popupMenu에 넣어줌

                // 메뉴 버튼 클릭 시 발생할 이벤트 처리
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.menu_pop_1:
                                Toast.makeText(getApplicationContext(),
                                        "팝업 메뉴 클릭", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.menu_pop_2:
                                float rota = btn_1.getRotation();   // 각도 가져오고
                                btn_1.setRotation(rota + 45);  // 로테이션으로 각도 더해주기
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
           }
        });

        // 만들어진 버튼 위젯에 컨텍스트 메뉴 등록하기
        registerForContextMenu(btn_1);  //onCreate에서 사용해야함
    }

    // 빠른 오버라이드하려면 ctrl + o

    // 컨텍스트메뉴를 등록할떄 호출되는 함수
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        // 제목 달기 (아이콘, xml View도 가능)
        menu.setHeaderTitle("롱 클릭 했을 때 뜨는 메뉴");
        inflater.inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_con_1:
                linear.setBackgroundColor(Color.RED);
                break;
            case R.id.menu_con_2:
                linear.setBackgroundColor(Color.BLUE);
                break;
            case R.id.menu_con_3:
                linear.setBackgroundColor(Color.GREEN);
                break;
        }
        return false;
    }

    // xml파일이 로드될때 메뉴버튼이 들어갈 항목을 로드하는 함수
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        // 메뉴 추가 설정      xml를 사용하기 위한 과정
        MenuInflater inflater = getMenuInflater();
        // 현재화면에 메뉴버튼에 menu_basic.xml을 등록
        // (어떤 메뉴 불러올지, 누가 관리하는지)
        inflater.inflate(R.menu.menu_basic, menu);
        return true;
    }

    // 툴바에서 보여주는 건 옵션 메뉴
    // 메뉴 중 하나를 선택했을 떄 호출되는 함수
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // item.getItemId() : 사용자가 어떤 메뉴를 선택했늕 ID값을 추출
        switch (item.getItemId()) {
            case R.id.menu_1:
                Toast.makeText(getApplicationContext(),
                        "첫번쨰 메뉴버튼 클릭", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_2:
                Toast.makeText(getApplicationContext(),
                        "두번째 메뉴버튼 클릭", Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }
}
