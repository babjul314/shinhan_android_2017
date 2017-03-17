package com.shinhan.serviceexam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();//처음실행될때 인텐트 수신
        processIntent(intent);//수신된 인텐트 처리
    }
    @Override
    protected void onNewIntent(Intent intent) {//이미 실행중일때 인텐트 수신
        processIntent(intent);//수신된 인텐트 처리
        super.onNewIntent(intent);
    }
    private void processIntent(Intent intent) {
        if (intent != null) {
            String command = intent.getStringExtra("command");
            String name = intent.getStringExtra("name");
            if (command != null && name != null) {
                Toast.makeText(MainActivity.this, "command:" + command + ",name:" + name,
                        Toast.LENGTH_LONG).show();
            }
        }
    }
    public void onButtonClicked(View view) {
        EditText editText = (EditText)findViewById(R.id.edittext);
        String name = editText.getText().toString();
        Intent intent = new Intent(MainActivity.this, MyService.class);
        intent.putExtra("command", "show");
        intent.putExtra("name", name);
        startService(intent);
    }
}
