package com.app.saintjimmy.iphome.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.saintjimmy.iphome.R;

public class LoginActivity extends AppCompatActivity {

    private EditText UsernameLogin, SenhaLogin;
    private TextView tvLoginLogin;
    private ImageView ivBarraCadastro;
    private Button Login;

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
        tvLoginLogin = findViewById(R.id.tv_loginlogin);
        ivBarraCadastro = findViewById(R.id.iv_barracadastro);
        Login = findViewById(R.id.bt_login);

    }


}
