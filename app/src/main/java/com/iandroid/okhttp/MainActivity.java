package com.iandroid.okhttp;

import android.os.Bundle;

import com.iandroid.allclass.okhttp.HttpConfig;
import com.iandroid.allclass.okhttp.HttpManager;
import com.iandroid.allclass.okhttp.caller.HttpCaller;
import com.iandroid.allclass.okhttp.interceptor.OkHttpInterceptor;
import com.iandroid.allclass.okhttp.retrofit.ServiceFactory;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inithttp();
    }

    public void inithttp() {
        HttpManager.init(new HttpConfig.Builder()
                .baseUrl("UrlConfig.BASE_HTTP_DOMAIN")
                .okHttpInterceptor(OkHttpInterceptor.get())
                .domainProvider(new DomainProvider())
                .addInterceptor(new FidDateUrlInterceptor())
                .requestResponse(new RequesResponse())
                .headerProvider(HeaderManager.getInstance())
                .errorInterceptor(DefaultErrorHandler.get())
                .build());
    }

    private void apicall() {
        HttpManager.call(new HttpCaller()
                .request(s -> ServiceFactory.get(DataService.class).getMyAccountInfo())
                .lifecycleOwner(this)
                .failure(e -> {
                })
                .success(r -> {

                }));
    }
}
