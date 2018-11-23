package com.app.saintjimmy.iphome.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.app.saintjimmy.iphome.ExpandableAdapter;
import com.app.saintjimmy.iphome.Model.Ingrediente;
import com.app.saintjimmy.iphome.Model.Usuario;
import com.app.saintjimmy.iphome.Model.UsuarioComida;
import com.app.saintjimmy.iphome.R;
import com.app.saintjimmy.iphome.Services.IngredienteService;
import com.app.saintjimmy.iphome.Services.UsuarioIngredienteService;
import com.app.saintjimmy.iphome.Services.UsuarioService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MenuIngredienteActivity extends AppCompatActivity {

    private TextView tvSelecionar;
    private Button btBuscarReceita;
    private ScrollView svIngredientes;
    private IngredienteService service;
    private UsuarioIngredienteService serviceFavoritos;
    private Retrofit retrofit;
    private Usuario usuario;
    private Ingrediente ingrediente;
    private Ingrediente[] ingredientes ={ingrediente};
    private ExpandableListView lvCategoria;
    private List<String> listCategoria;
    private HashMap<String, List<Ingrediente>> listIngrediente;
    private int i;
    private List<Ingrediente> listaAuxiliar;
    private List<UsuarioComida> listaMaisAuxiliar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_ingrediente);
        Toolbar toolbarMenuIngrediente = findViewById(R.id.toolbarMenuIngrediente);
        setSupportActionBar(toolbarMenuIngrediente);
        inicializarVariaveis();
        lvCategoria.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {

            }
        });
        lvCategoria.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int i) {

            }
        });
        lvCategoria.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                lvCategoria.setSelectedChild(i,i1,false);
                return false;
            }
        });

    }

    private void inicializarVariaveis() {
        Intent intent =getIntent();
        Bundle bundle = intent.getExtras();
        usuario = (Usuario) bundle.getSerializable("usuario");
        tvSelecionar = findViewById(R.id.tv_selecionar);
        btBuscarReceita = findViewById(R.id.bt_buscarreceita);
        svIngredientes = findViewById(R.id.sv_ingredientes);
        retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.IP))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(IngredienteService.class);
        serviceFavoritos = retrofit.create(UsuarioIngredienteService.class);
        listCategoria = new ArrayList<String>();
        listIngrediente = new HashMap<String, List<Ingrediente>>();

        //Categoria
        listCategoria.add("Favoritos");
        listCategoria.add("Alcool");
        listCategoria.add("Bebidas");
        listCategoria.add("Biscoitos");
        listCategoria.add("Carnes");
        listCategoria.add("Doces e Chocolates");
        listCategoria.add("Farinhas e Cereais");
        listCategoria.add("Frios e Embutidos");
        listCategoria.add("Frutas");
        listCategoria.add("Laticinios");
        listCategoria.add("Massas");
        listCategoria.add("Molhos");
        listCategoria.add("Oleo Vegetal");
        listCategoria.add("Origem Animal");
        listCategoria.add("Paes");
        listCategoria.add("Vegetais");


//        listaAuxiliar = new ArrayList<>();
//        listaMaisAuxiliar = new ArrayList<>();
//        serviceFavoritos.listaIngredientesFav(usuario.getIdUsuario()).enqueue(new Callback<List<UsuarioComida>>() {
//            @Override
//            public void onResponse(Call<List<UsuarioComida>> call, Response<List<UsuarioComida>> response) {
//                listaMaisAuxiliar = response.body();
//                    for (i = 0; i < listaMaisAuxiliar.size(); i++) {
//                        service.buscarPorId(listaMaisAuxiliar.get(i).getIdComida()).enqueue(new Callback<Ingrediente>() {
//                            @Override
//                            public void onResponse(Call<Ingrediente> call, Response<Ingrediente> response) {
//                                listaAuxiliar.add(response.body());
//                            }
//
//                            @Override
//                            public void onFailure(Call<Ingrediente> call, Throwable t) {
//
//                            }
//                        });
//                    }
//                    listIngrediente.put(listCategoria.get(0), listaAuxiliar);
//                }
//
//            @Override
//            public void onFailure(Call<List<UsuarioComida>> call, Throwable t) {
//
//            }
//        });

        //Ingredientes
        for(i=0;i<listCategoria.size();i++) {
            listaAuxiliar = new ArrayList<>();
            service.buscarTodosPorCategoria(listCategoria.get(i)).enqueue(new Callback<List<Ingrediente>>() {
                @Override
                public void onResponse(Call<List<Ingrediente>> call, Response<List<Ingrediente>> response) {
                    listaAuxiliar = response.body();
                    listIngrediente.put(listCategoria.get(i),listaAuxiliar);
                }

                @Override
                public void onFailure(Call<List<Ingrediente>> call, Throwable t) {
                    Toast.makeText(MenuIngredienteActivity.this, "Verifique sua conexão com internet", Toast.LENGTH_SHORT).show();
                }
            });
        }

        lvCategoria = findViewById(R.id.lv_categoria);
        lvCategoria.setAdapter(new ExpandableAdapter(MenuIngredienteActivity.this,listCategoria,listIngrediente));
    }
}