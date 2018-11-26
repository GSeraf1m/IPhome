package com.app.saintjimmy.iphome.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.app.saintjimmy.iphome.Model.Usuario;
import com.app.saintjimmy.iphome.R;

public class DrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final int TIME_LIMIT = 1500;
    private static long backPressed;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        Toolbar toolbar = findViewById(R.id.toolbarMain);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Pega o enviado pelo intent
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        usuario = (Usuario) bundle.getSerializable("usuario");

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MenuIngredienteFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_receita);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(TIME_LIMIT + backPressed > System.currentTimeMillis()){
                this.finishAffinity();
            }else{
                Toast.makeText(getApplicationContext(),"Pressione novamente para sair.",Toast.LENGTH_LONG).show();
            }
            backPressed = System.currentTimeMillis();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_receita:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MenuIngredienteFragment()).commit();
                break;
            case R.id.nav_perfil:
                Bundle bundle = new Bundle();
                bundle.putSerializable("usuario",usuario);
                Fragment myFrag = new ContaFragment();
                myFrag.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, myFrag).commit();
                break;
            case R.id.nav_salvas:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ReceitasSalvasFragment()).commit();
                break;
            case R.id.nav_sair:
                Intent itPrincipal = new Intent(DrawerActivity.this, MenuActivity.class);
                startActivity(itPrincipal);
                break;
            case R.id.nav_creditos:
                Toast.makeText(this,"Não temos artistas para dar crédito ainda",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_share:
                Toast.makeText(this,"Não temos link para sermos compartilhados ainda =(",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_avalie:
                Toast.makeText(this,"Não estamos na Play Store ainda ;-;",Toast.LENGTH_SHORT).show();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
