package com.app.saintjimmy.iphome.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.app.saintjimmy.iphome.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContaActivity extends AppCompatActivity {

    private EditText etUsernameConta, etEmailConta, etSenhaConta;
    private Button btAtualizarConta;
    private ImageView ivIcone1, ivIcone2, ivIcone3, ivIcone4, ivIcone5, ivIcone6;
    private Toolbar toolbarConta;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conta);
        inicializarVariaveis();
    }

    private void inicializarVariaveis() {
        etUsernameConta = findViewById(R.id.et_username_conta);
        etEmailConta = findViewById(R.id.et_email_conta);
        etSenhaConta = findViewById(R.id.et_senha_conta);
        btAtualizarConta = findViewById(R.id.bt_atualizar_conta);
        retrofit = new Retrofit.Builder()
                .baseUrl("@string/IP")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
