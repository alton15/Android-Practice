package com.example.sqlitesample;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// DB에 접근 및 테이블 생성, 데이터 입력, 수정, 삭제, 조회
public class MemojangDB extends SQLiteOpenHelper {

    // 생성자
    // 입력한 DB 이름을 접근하는 처리. 만약 DB가 존재하지 않으면 새로 생성
    public MemojangDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // DB에 테이블을 생성하는 부분
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE 'MEMOJANG' (\n" +
                "\t id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t date DATE,\n" +
                "\t title TEXT,\n" +
                "\t content TEXT\n" +
                ");");
    }

    // 테이블 드랍 후 새로 생성
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // 데이터 삽입 메소드
    public void insert(String date, String title, String content) {
        // DB에 접근
        SQLiteDatabase db = getWritableDatabase();

        // sql 문장을 실행
        String sql = String.format("INSERT INTO MEMOJANG " +
                "(date, title, content)" +
                "VALUES ('%s','%s','%s');", date, title, content);     // 정적 함수로 묶어주기
        db.execSQL(sql);

        // DB 접근 해제
        db.close();
    }

    // 데이터 삭제 메소드
    public void delete(String title) {
        SQLiteDatabase db = getWritableDatabase();

        // DELETE : 특정 테이블에 데이터를 삭제할 때 사용하는 문법
        // WHERE : 지우는 대상, 수정대상, 조회대상의 조건을 넣을 때 사용하는 문법
        String sql = String.format("DELETE FROM MEMOJANG WHERE title='%s';",
                title);
        db.execSQL(sql);

        db.close();
    }

    // 데이터 수정 메소드
    public void update(String title, String content) {
        SQLiteDatabase db = getWritableDatabase();

        // UPDATE : 테이블에 저장된 기존 데이터를 수정할 때 사용하는 문법
        // ex) 비밀번호 변경, 닉네임 변경
        // 주의 : WHERE이 빠지면 모든 행의 열 데이터를 수정하게 된다
        String sql = String.format("UPDATE MEMOJANG SET content='%s' WHERE title='%s';",
                content, title);
        db.execSQL(sql);

        db.close();
    }

    // 데이터 조회 메소드
    public String select() {
        // DB에 접근 = 읽기 모드
        SQLiteDatabase db = getReadableDatabase();

        // 조회 sql로 실행 및 결과 받기
        String result = "";
        // SELECT : 테이블에 있는 데이터들을 추출하는 sql 문법
        // MEMOJANG 테이블에 있는 모든 데이터 추출
        // Cursor : 검색 결과를 추출할 때 사용하는 객체, 행단위로 추출 가능
        Cursor cursor = db.rawQuery("SELECT * FROM MEMOJANG;", null);
        // 반복문 - cursor로 더이상 읽을 행이 없을때까지 반복
        while(cursor.moveToNext()) {
            // 커서가 가르키는 행 데이터를 추출할 때 getInt, getString 같은 함수로 열 데이터 추출
            // 열 데이터를 추출할 때는 열 번호를 입력해야함
            result += String.format("%s|%s|%s|%s\n",
                    cursor.getString(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3));
        }
        // DB 접근 해제
        db.close();
        return result;
    }
}
