package com.iandroid.okhttp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inithttp();
    }

    public void inithttp() {
        /*HttpManager.init(new HttpConfig.Builder()
                .baseUrl("https://www.baidu.com")
                .okHttpInterceptor(OkHttpInterceptor.get())
                .errorInterceptor(DefaultErrorHandler.get())
                .build());*/
    }
}
