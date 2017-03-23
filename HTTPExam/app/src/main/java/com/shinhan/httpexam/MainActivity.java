package com.shinhan.httpexam;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onButtonClicked(View view) {
        EditText editText = (EditText)findViewById(R.id.input01);
        String urlString = editText.getText().toString();
        if (urlString.indexOf("http") != -1) {//http라는 문자열이 포함되어 있는지확인
            new LoadHTML().execute(urlString);//입력한 url에 접속
        }
    }
    class LoadHTML extends AsyncTask<String,String,String> {
        ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        @Override
        protected void onPreExecute() {//백그라운드 작업 전에 호출
            super.onPreExecute();
            dialog.setMessage("HTML 요청 중...");
            dialog.show();//프로그레스 다이얼로그 보여주기
        }
        @Override
        protected void onPostExecute(String s) {//백그라운드 작업 후에 호출
            super.onPostExecute(s);
            dialog.dismiss();//프로그레스 다이얼로그 닫기
            TextView textView = (TextView)findViewById(R.id.txtMsg);
            textView.setText(s);//서버에서 받아온 HTML문자열을 TextView에 출력
        }
        @Override
        protected String doInBackground(String... params) {//실제 통신이 처리되는 부분
            StringBuilder output = new StringBuilder();
            try {//통신 부분은 반드시 try-catch로 예외처리 해주어야 한다
                URL url = new URL(params[0]);//전달받은 urlString으로 URL 객체 생성
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                if (conn != null) {
                    conn.setConnectTimeout(10000);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    int resCode = conn.getResponseCode();
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(conn.getInputStream()));
                    String line;//웹서버로부터 수신되는 데이터를 처리
                    while((line = reader.readLine()) != null) {
                        output.append(line);//한줄씩 읽어서 StringBuilder 객체에 추가
                    }
                    reader.close();
                    conn.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return output.toString();
        }
    }
}
