package com.yzlm.haberturkrss;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import java.net.InetAddress;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.provider.Settings;

public class Intro extends AppCompatActivity {


    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Intro.this, MainActivity.class);
                if (isNetworkConnected()||isInternetAvailable()){
                    startActivity(i);
                    finish();
                }else{
                    Snackbar snackbar = Snackbar
                            .make(findViewById(R.id.layout), "Lütfen internet bağlantınızı açınız", Snackbar.LENGTH_INDEFINITE).setAction("İnternetinizi açınız", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                                    startActivityForResult(intent,1);

                                }
                            });
                    snackbar.show();
                }
            }
        }, SPLASH_TIME_OUT);

    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("http://www.google.com/");

            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }

    }
}
