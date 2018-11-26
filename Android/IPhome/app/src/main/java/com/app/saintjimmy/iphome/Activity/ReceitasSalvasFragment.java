package com.app.saintjimmy.iphome.Activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.app.saintjimmy.iphome.Model.Comida;
import com.app.saintjimmy.iphome.Model.Ingrediente;
import com.app.saintjimmy.iphome.Model.Receita;
import com.app.saintjimmy.iphome.Model.Usuario;
import com.app.saintjimmy.iphome.R;
import com.app.saintjimmy.iphome.Services.IngredienteService;
import com.app.saintjimmy.iphome.Services.ReceitaService;
import com.app.saintjimmy.iphome.Services.UsuarioReceitaService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReceitasSalvasFragment extends Fragment {
    ListView lvReceitasSalvas;
    List<Comida> listReceitasSalvas;
    Retrofit retrofit;
    UsuarioReceitaService urService;
    Usuario usuario;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        return inflater.inflate(R.layout.fragment_receitas_salvas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = this.getArguments();
        usuario = (Usuario) bundle.getSerializable("usuario");
        lvReceitasSalvas = view.findViewById(R.id.listas_receitasfavoritas);
        retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.IP))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        urService = retrofit.create(UsuarioReceitaService.class);
        urService.listaReceitasFav(usuario.getIdUsuario()).enqueue(new Callback<List<Comida>>() {
            @Override
            public void onResponse(Call<List<Comida>> call, Response<List<Comida>> response) {
                listReceitasSalvas = response.body();
            }

            @Override
            public void onFailure(Call<List<Comida>> call, Throwable t) {
                Toast.makeText(getContext(),"Erro de conexão",Toast.LENGTH_SHORT).show();
            }
        });

        ArrayAdapter<Comida> adapter = new ArrayAdapter<Comida>(getContext(),android.R.layout.simple_list_item_2,listReceitasSalvas);
        lvReceitasSalvas.setAdapter(adapter);

        lvReceitasSalvas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //TODO intent pra receita fragment
            }
        });
    }
}
