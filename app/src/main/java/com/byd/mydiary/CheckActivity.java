package com.byd.mydiary;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheckActivity extends AppCompatActivity {

    private Button rtn_btn;
    private TextView title;
    private TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        title = (TextView) findViewById(R.id.title_check);
        content = (TextView) findViewById(R.id.content_check);
        rtn_btn = (Button)findViewById(R.id.ret_check);

        //通过日记item跳转
        Intent intent = getIntent();
        String[] date = {intent.getStringExtra("date")};

        DBOpenHelper dbOpenHelper = new DBOpenHelper(CheckActivity.this);
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        Diary diary = DiaryHelper.queryFromDate(db,date);

        title.setText(diary.getTitle());
        content.setText(diary.getContent());

        rtn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CheckActivity.this,MainActivity.class));
                finish();
            }
        });
    }
}
