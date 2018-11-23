package com.app.saintjimmy.iphome.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.saintjimmy.iphome.Model.Usuario;
import com.app.saintjimmy.iphome.R;
import com.app.saintjimmy.iphome.Services.UsuarioService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmailLogin, etSenhaLogin;
    private TextView tvLoginLogin;
    private ImageView ivBarraCadastro;
    private Button btLogin;
    private Usuario usuario;
    private UsuarioService service;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicializarVariaveis();
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = new Usuario();
                usuario.setEmail(etEmailLogin.getText().toString());
                usuario.setSenha(etSenhaLogin.getText().toString());
                service.buscarPorLoginESenha(usuario.getEmail(),usuario.getSenha()).enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        if(response.isSuccessful()) {
                            usuario = response.body();
                            Intent itPrincipal = new Intent(LoginActivity.this, MenuIngredienteActivity.class);
                            startActivity(itPrincipal);
                            itPrincipal.putExtra("usuario", usuario);
                            startActivity(itPrincipal);
                        }else{
                            Toast.makeText(LoginActivity.this,"Email ou senha incorretos.",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        Toast.makeText(LoginActivity.this,"Que Pena! Erro de Conexão",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void inicializarVariaveis() {
        etEmailLogin = findViewById(R.id.et_emaillogin);
        etSenhaLogin = findViewById(R.id.et_senhalogin);
        tvLoginLogin = findViewById(R.id.tv_loginlogin);
        ivBarraCadastro = findViewById(R.id.iv_barracadastro);
        btLogin = findViewById(R.id.bt_login);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.25.34:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(UsuarioService.class);
    }


}
