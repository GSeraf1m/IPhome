package com.app.saintjimmy.iphome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class CadastrarActivity extends AppCompatActivity {

    EditText UsernameCadastro, EmailCadastro, SenhaCadastro;
    Button Cadastrar;
    CheckBox Novidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
        inicializarVariaveis();
        Cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itPrincipal = new Intent(CadastrarActivity.this, MenuIngredienteActivity.class);
                startActivity(itPrincipal);
            }
        });
    }

    private void inicializarVariaveis() {
        UsernameCadastro = findViewById(R.id.et_usernamecadastro);
        EmailCadastro = findViewById(R.id.et_emailcadastro);
        SenhaCadastro = findViewById(R.id.et_senhacadastro);
        Cadastrar = findViewById(R.id.bt_cadastrar);
        Novidades = findViewById(R.id.cb_novidades);
    }
}
