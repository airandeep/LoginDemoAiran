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

public class LoginActivity extends BaseActivity {

    private IntentFilter intentFilter;
    private AiranReceiver airanReceiver;

    private EditText txtAccount;
    private EditText txtPwd;
    private Button btnToLogin2;
    private Button btnLodin;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        intentFilter = new IntentFilter();
        intentFilter.addAction("com.airan.clear");
        airanReceiver = new AiranReceiver();
        registerReceiver(airanReceiver,intentFilter);

        pref = PreferenceManager.getDefaultSharedPreferences(this);

        txtAccount = findViewById(R.id.txtAccount);
        txtPwd = findViewById(R.id.txtPwd);
        btnLodin = findViewById(R.id.btnLogin);
        btnToLogin2 = findViewById(R.id.btnToLogin2);

        if(pref.getAll().size()>0){
            txtAccount.setText(pref.getString("account",""));
            txtPwd.setText(pref.getString("pwd",""));
        }else{
            txtAccount.setText("");
            txtPwd.setText("");
        }


        btnLodin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtA = txtAccount.getText().toString().trim();
                String txtP = txtPwd.getText().toString().trim();
                editor = pref.edit();
                if(txtA.equals("airan")&&txtP.equals("347254")){

                    editor.putString("account",txtA);
                    editor.putString("pwd",txtP);
                    editor.apply();
                    //Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this,SuccessActivity.class);
                    startActivity(intent);
                }else{
                    editor.clear();
                    Toast.makeText(LoginActivity.this,"密码或账户错误",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnToLogin2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(LoginActivity.this,LoginActivity2.class);
                startActivity(intent);
            }
        });
    }

    class AiranReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent){
            if(pref.getAll().size()>0){
                editor = pref.edit();
                editor.clear();
                editor.apply();
            }
        }
    }


}
