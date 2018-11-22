package com.app.saintjimmy.iphome.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.app.saintjimmy.iphome.R;

public class MenuActivity extends AppCompatActivity {

    private Button LoginMenu, CadastrarMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        inicializarVariaveis();

        LoginMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itLogin = new Intent(MenuActivity.this, LoginActivity.class);
                startActivity(itLogin);
            }
        });

        CadastrarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itCadastro = new Intent(MenuActivity.this, CadastrarActivity.class);
                startActivity(itCadastro);
            }
        });
    }

    private void inicializarVariaveis() {
        LoginMenu = findViewById(R.id.bt_loginmenu);
        CadastrarMenu = findViewById(R.id.bt_cadastrarmenu);
    }
}
