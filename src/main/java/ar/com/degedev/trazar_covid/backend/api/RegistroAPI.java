package ar.com.degedev.trazar_covid.backend.api;

import ar.com.degedev.trazar_covid.frontend.model.Registro;
import retrofit2.Call;
import retrofit2.http.*;

import java.time.LocalDateTime;
import java.util.List;

public interface RegistroAPI {
    @GET("registro")
    Call<List<Registro>> listarRegistros();

    @GET("registro/cliente/{dni}")
    Call<List<Registro>> listarRegistrosConCliente(@Path("dni") Integer clienteDNI);

    @GET("registro/comercio/{id}")
    Call<List<Registro>> listarRegistrosConComercio(@Path("id") Integer comercioId);

    @GET("registro/fecha")
    Call<List<Registro>> listarRegistrosEntreFechas(
            @Query("desde") LocalDateTime inicio,
            @Query("hasta") LocalDateTime fin
    );

    @GET("registro/fecha/comercio")
    Call<List<Registro>> listarRegistrosEntreFechasYComercio(
            @Query("desde") LocalDateTime inicio,
            @Query("hasta") LocalDateTime fin,
            @Query("id") Integer comercioId
    );

    @GET("registro/fecha/cliente")
    Call<List<Registro>> listarRegistrosEntreFechasYCliente(
            @Query("desde") LocalDateTime inicio,
            @Query("hasta") LocalDateTime fin,
            @Query("dni") Integer clienteDni
    );

    @POST("registro")
    Call<Registro> altaRegistro(@Body Registro registro);
}
