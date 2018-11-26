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
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements Validator.ValidationListener {

    @NotEmpty
    @Email
    private EditText etEmailLogin;
    @NotEmpty
    private EditText etSenhaLogin;
    private TextView tvLoginLogin;
    private ImageView ivBarraCadastro;
    private Button btLogin;
    private Usuario usuario;
    private UsuarioService service;
    private Retrofit retrofit;
    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        validator = new Validator(this);
        validator.setValidationListener(this);
        inicializarVariaveis();
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
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
                .baseUrl(getResources().getString(R.string.IP))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(UsuarioService.class);
    }


    @Override
    public void onValidationSucceeded() {
        usuario = new Usuario();
        usuario.setEmail(etEmailLogin.getText().toString());
        usuario.setSenha(etSenhaLogin.getText().toString());
        service.buscarPorLoginESenha(usuario.getEmail(),usuario.getSenha()).enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()) {
                    usuario = response.body();
                    Intent itPrincipal = new Intent(LoginActivity.this, DrawerActivity.class);
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

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for(ValidationError error : errors){
            View view = error.getView();
            String mensagem = error.getCollatedErrorMessage(this);

            if(view instanceof EditText){
                ((EditText) view).setError(mensagem);
            }else{
                Toast.makeText(this,mensagem,Toast.LENGTH_LONG);
            }
        }
    }
}
