package org.techtown.ryongnote.note;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.techtown.ryongnote.R;

public class NoteDetails extends AppCompatActivity {
    Intent data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        Toolbar toolbar = findViewById(R.id.toolbar);  // activity_note_details.xml
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // 뒤로가기 버튼 만들기

        // 노트 클릭 시 노트 내용 자세히 보기 위한 설정
        TextView title = findViewById(R.id.noteDetailsTitle);  // activity_note_details.xml
        TextView content = findViewById(R.id.noteDetailsContent);  // content_note_details.xml
        content.setMovementMethod(new ScrollingMovementMethod());

        Intent data = getIntent();
        title.setText(data.getStringExtra("title"));  // Adapter에서 putExtra로 넣은 position에 따른 title 가져오기
        content.setText(data.getStringExtra("content"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            content.setBackgroundColor(getResources().getColor(data.getIntExtra("code", 0), null));  // 표지와 같은 색으로 클릭 시 배경색을 통일
        }
        // ##########################################

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home) {  // toolbar의 back 키를 눌렀을 때 동작
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}