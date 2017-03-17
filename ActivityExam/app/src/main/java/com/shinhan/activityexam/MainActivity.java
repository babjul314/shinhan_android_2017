package com.shinhan.activityexam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onButton1Clicked(View view) {
        Log.i("onButton1Clicked","-----------------!!!!!");
        EditText editText = (EditText)findViewById(R.id.edittext);
        String string = editText.getText().toString();
        Log.i("editText", string);
        Intent intent = new Intent(MainActivity.this, SubActivity.class);
        intent.putExtra("String", string);
        startActivityForResult(intent, 0);//startActivity(intent);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {//SubActivity가 종료되었으면
            if (resultCode == RESULT_OK) {//값을 넘기는 정상 종료일때만
                String result = data.getStringExtra("Result");
                Log.i("onActivityResult", result);
                EditText editText = (EditText)findViewById(R.id.edittext);
                editText.setText(result);
            }
        }
    }
}
