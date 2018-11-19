package com.app.saintjimmy.iphome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class ListaReceitaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_receita);
        Toolbar toolbarReceitas = findViewById(R.id.toolbarReceitas);
        setSupportActionBar(toolbarReceitas);
    }
}
