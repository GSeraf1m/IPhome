package com.app.saintjimmy.iphome.Services;

import com.app.saintjimmy.iphome.Model.Ingrediente;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;



public interface IngredienteService {

    @GET("ingrediente/listar/")
    Call<List<Ingrediente>> listaTodosIngredientes();

    @GET("ingrediente/{id}")
    Call<Ingrediente> buscarPorId(@Path("id") long id);

    @GET("ingrediente/categoria/{categoria}")
    Call<Ingrediente> buscarTodosPorCategoria(@Path("categoria")String categoria);
}
