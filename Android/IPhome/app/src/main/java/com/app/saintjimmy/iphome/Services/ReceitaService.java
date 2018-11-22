package com.app.saintjimmy.iphome.Services;

import com.app.saintjimmy.iphome.Model.Receita;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;



public interface ReceitaService {

    @GET("receita/listar/")
    Call<List<Receita>> listaTodasReceitas();

    @GET("receita/{id}")
    Call<Receita> buscarPorId(@Path("id") long id);

    @POST("receita/listar/ingrediente/")
    Call<List<Receita>> listaTodasReceitasPorIngrediente(@Body String categoria);
}
