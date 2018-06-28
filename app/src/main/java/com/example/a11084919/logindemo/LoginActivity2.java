package com.example.a11084919.logindemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity2 extends BaseActivity {

    private IntentFilter intentFilter;
    private AiranReceiver airanReceiver;

    private EditText txtPhoneNumber;
    private EditText txtCode;
    private Button btnLodin;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    private Button btnToLoginAiran;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        intentFilter = new IntentFilter();
        intentFilter.addAction("com.airan.clear");
        airanReceiver = new AiranReceiver();
        registerReceiver(airanReceiver,intentFilter);

        pref = PreferenceManager.getDefaultSharedPreferences(this);
        txtPhoneNumber = findViewById(R.id.txtPhoneNumber);
        txtCode = findViewById(R.id.txtCode);
        btnLodin = findViewById(R.id.btnLogin);

        btnToLoginAiran = findViewById(R.id.btnToLoginAiran);

        if(pref.getAll().size()>0){
            txtPhoneNumber.setText(pref.getString("phoneNumber",""));
            txtCode.setText(pref.getString("code",""));
        }else{
            txtPhoneNumber.setText("");
            txtCode.setText("");
        }


        btnLodin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String txtP = txtPhoneNumber.getText().toString().trim();
                String txtC = txtCode.getText().toString().trim();
                editor = pref.edit();
                if(txtP.equals("15651985528") && txtC.equals("111111")){

                    editor.putString("phoneNumber",txtP);
                    editor.putString("code",txtC);
                    editor.apply();
                    //Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity2.this,SuccessActivity.class);
                    startActivity(intent);
                }else{
                    editor.clear();
                    Toast.makeText(LoginActivity2.this,"手机号码或验证码错误",Toast.LENGTH_SHORT).show();

                }
            }
        });

        btnToLoginAiran.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(LoginActivity2.this,LoginActivity.class);
                startActivity(intent);
            }
        });



    }


    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(airanReceiver);
    }

    class AiranReceiver extends BroadcastReceiver{
        public void onReceive(Context context,Intent intent){
            if(pref.getAll().size()>0){
                editor = pref.edit();
                editor.clear();
                editor.apply();
            }
        }
    }


}
