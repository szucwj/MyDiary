package com.byd.mydiary;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SecondActivity extends Activity {

    private Button submit_btn;
    private Button rtn_btn;
    private TextView title;
    private TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        title = (TextView) findViewById(R.id.title);
        content = (TextView) findViewById(R.id.content);
        submit_btn = (Button) findViewById(R.id.submit);
        rtn_btn = (Button)findViewById(R.id.ret);

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String diary_title = title.getText().toString();
                String diary_content = content.getText().toString();
                SimpleDateFormat dateFormat = new SimpleDateFormat(
                        "yyyy年MM月dd日 HH:mm:ss");
                Date curDate = new Date(System.currentTimeMillis());
                String date = dateFormat.format(curDate);

                if (!"".equals(diary_title.trim())) {
                    DBOpenHelper dbOpenHelper = new DBOpenHelper(SecondActivity.this);
                    SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
                    DiaryHelper.insert(db, diary_title, diary_content, date);

                    startActivity(new Intent(SecondActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(SecondActivity.this, "标题不能为空", Toast.LENGTH_SHORT).show();
                }

            }
        });

        rtn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SecondActivity.this,MainActivity.class));
                finish();
            }
        });

    }
}
