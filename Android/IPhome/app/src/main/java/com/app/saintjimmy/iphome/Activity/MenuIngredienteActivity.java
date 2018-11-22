package com.app.saintjimmy.iphome.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.app.saintjimmy.iphome.Model.Ingrediente;
import com.app.saintjimmy.iphome.R;

public class MenuIngredienteActivity extends AppCompatActivity {

    private TextView tvSelecionar;
    private Button btBuscarReceita;
    private ScrollView svIngredientes;
    private Spinner spFavoritos, spAlcool, spBebidas, spBiscoitos, spCarnes, spDocesChocolates, spFarinhasCereais, spFriosEmbutidos, spFrutas,
            spLaticinios, spMassas, spMolhos, spOleoVegetal, spOrigemAnimal, spPaes, spVegetais;
    private ArrayAdapter<Ingrediente> adapterFavoritos, adapterAlcool, adapterBebidas, adapterBiscoitos, adapterCarnes, adapterDocesChocolates,
        adapterFarinhasCereais,adapterFriosEmbutidos,adapterFrutas,adapterLaticinios,adapterMassas,adapterMolhos,adapterOleoVegeteal,adapterOrigemAnimal,adapterPaes,adapterVegetais;
    private Ingrediente ingrediente;
    private Ingrediente[] ingredientes ={ingrediente};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_ingrediente);
        Toolbar toolbarMenuIngrediente = findViewById(R.id.toolbarMenuIngrediente);
        setSupportActionBar(toolbarMenuIngrediente);
        inicializarVariaveis();

    }

    private void inicializarVariaveis() {
        tvSelecionar = findViewById(R.id.tv_selecionar);
        btBuscarReceita = findViewById(R.id.bt_buscarreceita);
        svIngredientes = findViewById(R.id.sv_ingredientes);
        ingrediente = new Ingrediente(1,"Teste","CategoriaTeste");
        spFavoritos = findViewById(R.id.sp_favoritos);
        spAlcool = findViewById(R.id.sp_alcool);
        spBebidas = findViewById(R.id.sp_bebidas);
        spBiscoitos = findViewById(R.id.sp_biscoitos);
        spCarnes = findViewById(R.id.sp_carnes);
        spDocesChocolates = findViewById(R.id.sp_doces_chocolates);
        spFarinhasCereais = findViewById(R.id.sp_farinhas_cereais);
        spFriosEmbutidos = findViewById(R.id.sp_frios_embutidos);
        spFrutas = findViewById(R.id.sp_frutas);
        spLaticinios = findViewById(R.id.sp_laticinios);
        spMassas = findViewById(R.id.sp_massas);
        spMolhos = findViewById(R.id.sp_molhos);
        spOleoVegetal = findViewById(R.id.sp_oleo_vegetal);
        spOrigemAnimal = findViewById(R.id.sp_origem_animal);
        spPaes = findViewById(R.id.sp_paes);
        spVegetais = findViewById(R.id.sp_vegetais);
        adapterAlcool = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);
        adapterBebidas = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);
        adapterBiscoitos = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);
        adapterCarnes = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);
        adapterDocesChocolates = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);
        adapterFarinhasCereais = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);
        adapterFavoritos = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);
        adapterFriosEmbutidos = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);
        adapterFrutas = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);
        adapterLaticinios = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);
        adapterMassas = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);
        adapterMolhos = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);
        adapterOleoVegeteal = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);
        adapterOrigemAnimal = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);
        adapterPaes = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);
        adapterVegetais = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);
    }
}