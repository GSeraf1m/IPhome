package com.app.saintjimmy.iphome.Services;



import com.app.saintjimmy.iphome.Model.Comentario;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ComentarioService {

    @POST("comentario/")
    Call<Comentario> cadastrar(@Body Comentario comentario);

    /*@GET("comentario/listar/")
    Call<List<Comentario>> listaTodosComentarios();*/

    @DELETE("comentario/{id}")
    Call<Void> excluir(@Path("id") long id);

    @GET("comentario/{id}")
    Call<Comentario> buscarPorId(@Path("id") long id);

    @GET("comentario/{id}")
    Call<List<Comentario>> buscarPorIdReceita(@Path("id") long id);
}
