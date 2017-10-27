package com.example.sistemas.mapamigrac.api;

import com.example.sistemas.mapamigrac.models.Oficina;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by sistemas on 27/10/17.
 */

public interface ApiDatos {
    @GET("nnuz-ten8.json")
    Call<List<Oficina>> obtenerListaOficinas();

}
