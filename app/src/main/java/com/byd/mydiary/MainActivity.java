package com.byd.mydiary;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ListView listView;
    private Button add_diary;
    private Button delete_diary;
    private ArrayList<Diary> diaries;
    private DiaryAdapter diaryAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        //加载所有数据
        DBOpenHelper dbOpenHelper = new DBOpenHelper(MainActivity.this);
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        diaries = DiaryHelper.queryAllData(db);
        diaryAdapter = new DiaryAdapter(MainActivity.this, diaries);
        listView.setAdapter(diaryAdapter);

        listView.setOnItemClickListener(this);
        add_diary.setOnClickListener(this);
        delete_diary.setOnClickListener(this);
    }

    public void initView() {
        listView = (ListView) findViewById(R.id.diary_list);
        add_diary = (Button) findViewById(R.id.add_diary);
        delete_diary = (Button) findViewById(R.id.delete_diary);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_diary:
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
                finish();
                break;
            case R.id.delete_diary:
                final EditText id = new EditText(this);
                id.setGravity(Gravity.CENTER);
                id.setInputType(InputType.TYPE_CLASS_NUMBER);

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("请输入要删除的日记序号：").setIcon(android.R.drawable.ic_dialog_info).setView(id)
                        .setNegativeButton("Cancel", null);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String[] selectionArgs = {id.getText().toString().trim()};
                        DBOpenHelper dbOpenHelper = new DBOpenHelper(MainActivity.this);
                        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
                        Diary diary = DiaryHelper.queryFromID(db, selectionArgs);
                        if (diary == null) {
                            Toast.makeText(MainActivity.this, "序号不存在", Toast.LENGTH_SHORT).show();
                        } else {
                            String whereClaus = "date=?";
                            String[] whereArgs = {diary.getDate()};
                            DiaryHelper.delete(db, whereClaus, whereArgs);
                            Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                            //刷新，加载所有数据
                            ArrayList<Diary> arrayListDiaries = DiaryHelper.queryAllData(db);
                            DiaryAdapter diaryAdapter = new DiaryAdapter(MainActivity.this, arrayListDiaries);
                            listView.setAdapter(diaryAdapter);
                        }
                    }
                });
                builder.show();
                break;
            default:
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        String date = ((TextView) view.findViewById(R.id.create_date)).getText().toString();
        Intent intent = new Intent(MainActivity.this, CheckActivity.class);
        intent.putExtra("date", date);
        startActivity(intent);
        finish();
    }
}
