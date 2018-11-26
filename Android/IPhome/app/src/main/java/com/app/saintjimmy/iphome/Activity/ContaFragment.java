package com.app.saintjimmy.iphome.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

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

public class ContaFragment extends Fragment implements Validator.ValidationListener{

    @NotEmpty
    private EditText etUsernameConta;
    @NotEmpty
    @Email
    private EditText etEmailConta;
    @NotEmpty
    @Password(min = 6,scheme = Password.Scheme.ALPHA)
    private EditText etSenhaConta;
    @ConfirmPassword
    private EditText etConfirmarConta;
    private Button btAtualizarConta;
    private ImageView ivIcone1, ivIcone2, ivIcone3, ivIcone4, ivIcone5, ivIcone6;
    private Toolbar toolbarConta;
    private Retrofit retrofit;
    private Usuario usuario, usuario2;
    private UsuarioService service;
    private View view;
    private Bundle savedInstanceState;
    private Validator validator;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        return inflater.inflate(R.layout.fragment_conta, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.view = view;
        this.savedInstanceState = savedInstanceState;
        Bundle bundle = this.getArguments();
        validator = new Validator(this);
        validator.setValidationListener(this);
        usuario = (Usuario) bundle.getSerializable("usuario");
        etEmailConta = getView().findViewById(R.id.et_email_conta);
        etUsernameConta = getView().findViewById(R.id.et_username_conta);
        etSenhaConta = getView().findViewById(R.id.et_senha_conta);
        btAtualizarConta = getView().findViewById(R.id.bt_atualizar_conta);
        etConfirmarConta = getView().findViewById(R.id.et_confirmareditar);
        etUsernameConta.setText(usuario.getNomeUsuario());
        etSenhaConta.setText(usuario.getSenha());
        etConfirmarConta.setText(usuario.getSenha());
        retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.IP))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(UsuarioService.class);

        btAtualizarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validator.validate();
            }
        });
    }

    @Override
    public void onValidationSucceeded() {
        usuario2 = new Usuario();
        usuario2.setIdUsuario(usuario.getIdUsuario());
        usuario2.setEmail(etEmailConta.getText().toString());
        usuario2.setNomeUsuario(etUsernameConta.getText().toString());
        usuario2.setSenha(etSenhaConta.getText().toString());
        usuario2.setFotoUsuario("6");

        service.editar(usuario2).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Atualizar Conta");
                builder.setMessage("Você atualizou sua conta");
                builder.setCancelable(true);
                AlertDialog dialog = builder.create();
                dialog.show();
                usuario.setEmail(usuario2.getEmail());
                usuario.setNomeUsuario(usuario2.getNomeUsuario());
                usuario.setSenha(usuario2.getSenha());
                usuario.setFotoUsuario(usuario2.getFotoUsuario());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getContext(),"Erro de conexão ;(",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for(ValidationError error : errors){
            View view = error.getView();
            String mensagem = error.getCollatedErrorMessage(getContext());

            if(view instanceof EditText){
                ((EditText) view).setError(mensagem);
            }else{
                Toast.makeText(getContext(),mensagem,Toast.LENGTH_LONG);
            }
        }
    }
}
