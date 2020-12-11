package ar.com.degedev.trazar_covid.api;

import ar.com.degedev.trazar_covid.model.Registro;
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
            @Query("inicio") LocalDateTime inicio,
            @Query("fin") LocalDateTime fin
    );

    @POST("registro/alta")
    Call<Registro> altaRegistro(@Body Registro registro);
}
