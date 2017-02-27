package com.example.yls.as0227;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private WebView contentWebView;
    private Button mButton1;
    private Button mButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        contentWebView = (WebView) findViewById(R.id.webview);
        mButton1 = (Button) findViewById(R.id.button);
        mButton2 = (Button) findViewById(R.id.button2);
        contentWebView.getSettings().setJavaScriptEnabled(true);
        contentWebView.loadUrl("file:///android_asset/web.html");
        contentWebView.addJavascriptInterface(MainActivity.this,"android");

        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentWebView.loadUrl("javascript:javacalljs()");
            }
        });

        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentWebView.loadUrl("javascript:javacalljswith(" + "'http://blog.csdn.net/Leejizhou'" + ")");
            }
        });
    }
    @JavascriptInterface
    public void startFunction(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "show", Toast.LENGTH_SHORT).show();
            }
        });
    }
    
    @JavascriptInterface
    public void startFunction(final String text){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new AlertDialog.Builder(MainActivity.this).setMessage(text).show();
            }
        });
    }
}
