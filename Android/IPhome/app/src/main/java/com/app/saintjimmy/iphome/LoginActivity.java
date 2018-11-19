package com.app.saintjimmy.iphome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText UsernameLogin, SenhaLogin;
    Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicializarVariaveis();
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itPrincipal = new Intent(LoginActivity.this, MenuIngredienteActivity.class);
                startActivity(itPrincipal);
            }
        });
    }

    private void inicializarVariaveis() {
        UsernameLogin = findViewById(R.id.et_usernamelogin);
        SenhaLogin = findViewById(R.id.et_senhalogin);
        Login = findViewById(R.id.bt_login);
    }


}
