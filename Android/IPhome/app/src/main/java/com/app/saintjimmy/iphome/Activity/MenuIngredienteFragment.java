package com.app.saintjimmy.iphome.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.saintjimmy.iphome.ExpandableAdapter;
import com.app.saintjimmy.iphome.Model.Ingrediente;
import com.app.saintjimmy.iphome.Model.Usuario;
import com.app.saintjimmy.iphome.Model.UsuarioComida;
import com.app.saintjimmy.iphome.R;
import com.app.saintjimmy.iphome.Services.IngredienteService;
import com.app.saintjimmy.iphome.Services.UsuarioIngredienteService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MenuIngredienteFragment extends Fragment {

    private TextView tvSelecionar;
    private Button btBuscarReceita;
    private ScrollView svIngredientes;
    private IngredienteService service;
    private UsuarioIngredienteService serviceFavoritos;
    private Retrofit retrofit;
    private Usuario usuario;
    private ExpandableListView lvCategoria;
    private List<String> listCategoria;
    private HashMap<String, List<Ingrediente>> listIngrediente;
    private int i;
    private List<Ingrediente> listaAuxiliar;
    private List<UsuarioComida> listaMaisAuxiliar;
    private CheckBox cbSelecionar;
    private List<Long> idIngredientesSelecionados;
    private ExpandableAdapter eAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        return inflater.inflate(R.layout.fragment_menu_ingrediente, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = this.getArguments();
        usuario = (Usuario) bundle.getSerializable("usuario");
        cbSelecionar = getView().findViewById(R.id.cbItem);
        tvSelecionar = getView().findViewById(R.id.tv_selecionar);
        btBuscarReceita = getView().findViewById(R.id.bt_buscarreceita);
        svIngredientes = getView().findViewById(R.id.sv_ingredientes);
        retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.IP))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(IngredienteService.class);
        serviceFavoritos = retrofit.create(UsuarioIngredienteService.class);
        listCategoria = new ArrayList<String>();
        listIngrediente = new HashMap<String, List<Ingrediente>>();
        idIngredientesSelecionados = new ArrayList<>();

        lvCategoria = getView().findViewById(R.id.lv_categoria);
        eAdapter = new ExpandableAdapter(getContext(), listCategoria, listIngrediente);
        lvCategoria.setAdapter(eAdapter);

        //Categoria
//        listCategoria.add("Favoritos");
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
        listaAuxiliar = new ArrayList<Ingrediente>();
        service.buscarTodosPorCategoria("Alcool").enqueue(new Callback<List<Ingrediente>>() {
            @Override
            public void onResponse(Call<List<Ingrediente>> call, Response<List<Ingrediente>> response) {
                listaAuxiliar = response.body();
                listIngrediente.put(listCategoria.get(0), listaAuxiliar);
            }

            @Override
            public void onFailure(Call<List<Ingrediente>> call, Throwable t) {
                Toast.makeText(getContext(), "Verifique sua conexão com internet", Toast.LENGTH_SHORT).show();
            }
        });
        listaAuxiliar = new ArrayList<>();
        service.buscarTodosPorCategoria("Bebidas").enqueue(new Callback<List<Ingrediente>>() {
            @Override
            public void onResponse(Call<List<Ingrediente>> call, Response<List<Ingrediente>> response) {
                listaAuxiliar = response.body();
                listIngrediente.put(listCategoria.get(1), listaAuxiliar);
            }

            @Override
            public void onFailure(Call<List<Ingrediente>> call, Throwable t) {
                Toast.makeText(getContext(), "Verifique sua conexão com internet", Toast.LENGTH_SHORT).show();
            }
        });
        listaAuxiliar = new ArrayList<>();
        service.buscarTodosPorCategoria(listCategoria.get(2)).enqueue(new Callback<List<Ingrediente>>() {
            @Override
            public void onResponse(Call<List<Ingrediente>> call, Response<List<Ingrediente>> response) {
                listaAuxiliar = response.body();
                listIngrediente.put(listCategoria.get(2), listaAuxiliar);
            }

            @Override
            public void onFailure(Call<List<Ingrediente>> call, Throwable t) {
                Toast.makeText(getContext(), "Verifique sua conexão com internet", Toast.LENGTH_SHORT).show();
            }
        });
        listaAuxiliar = new ArrayList<>();
        service.buscarTodosPorCategoria(listCategoria.get(3)).enqueue(new Callback<List<Ingrediente>>() {
            @Override
            public void onResponse(Call<List<Ingrediente>> call, Response<List<Ingrediente>> response) {
                listaAuxiliar = response.body();
                listIngrediente.put(listCategoria.get(3), listaAuxiliar);
            }

            @Override
            public void onFailure(Call<List<Ingrediente>> call, Throwable t) {
                Toast.makeText(getContext(), "Verifique sua conexão com internet", Toast.LENGTH_SHORT).show();
            }
        });
        listaAuxiliar = new ArrayList<>();
        service.buscarTodosPorCategoria(listCategoria.get(4)).enqueue(new Callback<List<Ingrediente>>() {
            @Override
            public void onResponse(Call<List<Ingrediente>> call, Response<List<Ingrediente>> response) {
                listaAuxiliar = response.body();
                listIngrediente.put(listCategoria.get(4), listaAuxiliar);
            }

            @Override
            public void onFailure(Call<List<Ingrediente>> call, Throwable t) {
                Toast.makeText(getContext(), "Verifique sua conexão com internet", Toast.LENGTH_SHORT).show();
            }
        });
        listaAuxiliar = new ArrayList<>();
        service.buscarTodosPorCategoria(listCategoria.get(5)).enqueue(new Callback<List<Ingrediente>>() {
            @Override
            public void onResponse(Call<List<Ingrediente>> call, Response<List<Ingrediente>> response) {
                listaAuxiliar = response.body();
                listIngrediente.put(listCategoria.get(5), listaAuxiliar);
            }

            @Override
            public void onFailure(Call<List<Ingrediente>> call, Throwable t) {
                Toast.makeText(getContext(), "Verifique sua conexão com internet", Toast.LENGTH_SHORT).show();
            }
        });
        listaAuxiliar = new ArrayList<>();
        service.buscarTodosPorCategoria(listCategoria.get(6)).enqueue(new Callback<List<Ingrediente>>() {
            @Override
            public void onResponse(Call<List<Ingrediente>> call, Response<List<Ingrediente>> response) {
                listaAuxiliar = response.body();
                listIngrediente.put(listCategoria.get(6), listaAuxiliar);
            }

            @Override
            public void onFailure(Call<List<Ingrediente>> call, Throwable t) {
                Toast.makeText(getContext(), "Verifique sua conexão com internet", Toast.LENGTH_SHORT).show();
            }
        });
        listaAuxiliar = new ArrayList<>();
        service.buscarTodosPorCategoria(listCategoria.get(7)).enqueue(new Callback<List<Ingrediente>>() {
            @Override
            public void onResponse(Call<List<Ingrediente>> call, Response<List<Ingrediente>> response) {
                listaAuxiliar = response.body();
                listIngrediente.put(listCategoria.get(7), listaAuxiliar);
            }

            @Override
            public void onFailure(Call<List<Ingrediente>> call, Throwable t) {
                Toast.makeText(getContext(), "Verifique sua conexão com internet", Toast.LENGTH_SHORT).show();
            }
        });
        listaAuxiliar = new ArrayList<>();
        service.buscarTodosPorCategoria(listCategoria.get(8)).enqueue(new Callback<List<Ingrediente>>() {
            @Override
            public void onResponse(Call<List<Ingrediente>> call, Response<List<Ingrediente>> response) {
                listaAuxiliar = response.body();
                listIngrediente.put(listCategoria.get(8), listaAuxiliar);
            }

            @Override
            public void onFailure(Call<List<Ingrediente>> call, Throwable t) {
                Toast.makeText(getContext(), "Verifique sua conexão com internet", Toast.LENGTH_SHORT).show();
            }
        });
        listaAuxiliar = new ArrayList<>();
        service.buscarTodosPorCategoria(listCategoria.get(9)).enqueue(new Callback<List<Ingrediente>>() {
            @Override
            public void onResponse(Call<List<Ingrediente>> call, Response<List<Ingrediente>> response) {
                listaAuxiliar = response.body();
                listIngrediente.put(listCategoria.get(9), listaAuxiliar);
            }

            @Override
            public void onFailure(Call<List<Ingrediente>> call, Throwable t) {
                Toast.makeText(getContext(), "Verifique sua conexão com internet", Toast.LENGTH_SHORT).show();
            }
        });
        listaAuxiliar = new ArrayList<>();
        service.buscarTodosPorCategoria(listCategoria.get(10)).enqueue(new Callback<List<Ingrediente>>() {
            @Override
            public void onResponse(Call<List<Ingrediente>> call, Response<List<Ingrediente>> response) {
                listaAuxiliar = response.body();
                listIngrediente.put(listCategoria.get(10), listaAuxiliar);
            }

            @Override
            public void onFailure(Call<List<Ingrediente>> call, Throwable t) {
                Toast.makeText(getContext(), "Verifique sua conexão com internet", Toast.LENGTH_SHORT).show();
            }
        });
        listaAuxiliar = new ArrayList<>();
        service.buscarTodosPorCategoria(listCategoria.get(11)).enqueue(new Callback<List<Ingrediente>>() {
            @Override
            public void onResponse(Call<List<Ingrediente>> call, Response<List<Ingrediente>> response) {
                listaAuxiliar = response.body();
                listIngrediente.put(listCategoria.get(11), listaAuxiliar);
            }

            @Override
            public void onFailure(Call<List<Ingrediente>> call, Throwable t) {
                Toast.makeText(getContext(), "Verifique sua conexão com internet", Toast.LENGTH_SHORT).show();
            }
        });
        listaAuxiliar = new ArrayList<>();
        service.buscarTodosPorCategoria(listCategoria.get(12)).enqueue(new Callback<List<Ingrediente>>() {
            @Override
            public void onResponse(Call<List<Ingrediente>> call, Response<List<Ingrediente>> response) {
                listaAuxiliar = response.body();
                listIngrediente.put(listCategoria.get(12), listaAuxiliar);
            }

            @Override
            public void onFailure(Call<List<Ingrediente>> call, Throwable t) {
                Toast.makeText(getContext(), "Verifique sua conexão com internet", Toast.LENGTH_SHORT).show();
            }
        });
        listaAuxiliar = new ArrayList<>();
        service.buscarTodosPorCategoria(listCategoria.get(13)).enqueue(new Callback<List<Ingrediente>>() {
            @Override
            public void onResponse(Call<List<Ingrediente>> call, Response<List<Ingrediente>> response) {
                listaAuxiliar = response.body();
                listIngrediente.put(listCategoria.get(13), listaAuxiliar);
            }

            @Override
            public void onFailure(Call<List<Ingrediente>> call, Throwable t) {
                Toast.makeText(getContext(), "Verifique sua conexão com internet", Toast.LENGTH_SHORT).show();
            }
        });
        listaAuxiliar = new ArrayList<>();
        service.buscarTodosPorCategoria(listCategoria.get(14)).enqueue(new Callback<List<Ingrediente>>() {
            @Override
            public void onResponse(Call<List<Ingrediente>> call, Response<List<Ingrediente>> response) {
                listaAuxiliar = response.body();
                listIngrediente.put(listCategoria.get(14), listaAuxiliar);
            }

            @Override
            public void onFailure(Call<List<Ingrediente>> call, Throwable t) {
                Toast.makeText(getContext(), "Verifique sua conexão com internet", Toast.LENGTH_SHORT).show();
            }
        });

//        int tam = listCategoria.size();
//        for(i=0;i<tam;i++) {
//            service.buscarTodosPorCategoria(listCategoria.get(i)).enqueue(new Callback<List<Ingrediente>>() {
//                @Override
//                public void onResponse(Call<List<Ingrediente>> call, Response<List<Ingrediente>> response) {
//                    listaAuxiliar = response.body();
//                    listIngrediente.put(listCategoria.get(i),listaAuxiliar);
//                }
//
//                @Override
//                public void onFailure(Call<List<Ingrediente>> call, Throwable t) {
//                    Toast.makeText(MenuIngredienteFragment.this, "Verifique sua conexão com internet", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
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
                lvCategoria.setSelectedChild(i, i1, false);
                cbSelecionar = view.findViewById(R.id.cbItem);
                if (cbSelecionar.isSelected()) {
                    listIngrediente.get(i).get(i1).getId();
                    Toast.makeText(getContext(), "Debug:" + listIngrediente.get(i).get(i1).getId(), Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

    }
}