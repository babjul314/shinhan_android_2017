package com.shinhan.linearlayoutexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
    }

    public void onButtonClicked(View view) {
        ImageView imageView1 = (ImageView)findViewById(R.id.imageview1);
        ImageView imageView2 = (ImageView)findViewById(R.id.imageview2);
        Button button = (Button)view;
        //if (button.getId() == R.id.button1)
        if (button.getText().toString().equals("BUTTON1")) {//첫번째 버튼 클릭시
            imageView1.setBackgroundResource(R.drawable.f150_1);//BACKGROUND
            imageView2.setBackgroundResource(R.drawable.f150_3);
        } else {//두번째 버튼 클릭시
            imageView1.setBackgroundResource(R.drawable.f150_3);//BACKGROUND
            imageView2.setBackgroundResource(R.drawable.f150_2);
        }
    }
}
