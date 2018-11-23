package com.app.saintjimmy.iphome.Services;

import com.app.saintjimmy.iphome.Model.UsuarioComida;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UsuarioIngredienteService {

    @POST("usuario_ingrediente/")
    Call<UsuarioComida> cadastrar(@Body UsuarioComida ingrediente);

    @GET("usuario_ingrediente/listar/{id}")
    Call<List<UsuarioComida>> listaIngredientesFav(@Path("id") long id);

    @DELETE("usuario_ingrediente/{id}")
    Call<Void> excluir(@Path("id") long id);


}
