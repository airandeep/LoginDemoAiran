package com.example.a11084919.logindemo;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * A login screen that offers login via email/password.
 */
public class SuccessActivity extends BaseActivity {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;



    private Button btnLoginOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);


        btnLoginOut = findViewById(R.id.btnLoginOut);
        btnLoginOut.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){


                Intent intent = new Intent("com.airan.clear");
                sendBroadcast(intent);//发送广播
                ActivityCollector.finishAll();
                Intent intent1 = new Intent(SuccessActivity.this,LoginActivity.class);
                startActivity(intent1);



            }
        });
    }


















}

