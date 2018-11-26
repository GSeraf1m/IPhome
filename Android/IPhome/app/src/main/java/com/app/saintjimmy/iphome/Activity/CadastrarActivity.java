package com.app.saintjimmy.iphome.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.app.saintjimmy.iphome.Model.Usuario;
import com.app.saintjimmy.iphome.R;
import com.app.saintjimmy.iphome.Services.UsuarioService;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CadastrarActivity extends AppCompatActivity implements Validator.ValidationListener {

    @NotEmpty
    private EditText etUsernameCadastro;
    @NotEmpty
    @Email
    private EditText etEmailCadastro;
    @NotEmpty
    @Password(min = 6,scheme = Password.Scheme.ALPHA)
    private EditText etSenhaCadastro;
    @ConfirmPassword
    private EditText etConfirmarSenha;
    private Button btCadastrar;
    private CheckBox cbNovidades;
    private Usuario usuario;
    private UsuarioService service;
    private Retrofit retrofit;
    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
        validator = new Validator(this);
        validator.setValidationListener(this);
        inicializarVariaveis();

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });
    }

    private void inicializarVariaveis() {
        etUsernameCadastro = findViewById(R.id.et_usernamecadastro);
        etEmailCadastro = findViewById(R.id.et_emailcadastro);
        etSenhaCadastro = findViewById(R.id.et_senhacadastro);
        etConfirmarSenha = findViewById(R.id.et_confirmarsenha);
        btCadastrar = findViewById(R.id.bt_cadastrar);
        cbNovidades = findViewById(R.id.cb_novidades);
        retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.IP))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(UsuarioService.class);

    }

    @Override
    public void onValidationSucceeded() {
        usuario = new Usuario();
        usuario.setEmail(etEmailCadastro.getText().toString());
        usuario.setNomeUsuario(etUsernameCadastro.getText().toString());
        usuario.setSenha(etSenhaCadastro.getText().toString());
        usuario.setFotoUsuario("6");

        service.cadastrar(usuario).enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()) {
                    usuario = response.body();
                    Intent itPrincipal = new Intent(CadastrarActivity.this, DrawerActivity.class);
                    itPrincipal.putExtra("usuario", usuario);
                    startActivity(itPrincipal);
                }


            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(CadastrarActivity.this,"Erro de Conexão",Toast.LENGTH_SHORT).show();
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
