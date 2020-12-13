package ar.com.degedev.trazar_covid.backend.api;

import ar.com.degedev.trazar_covid.frontend.model.Comercio;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ComercioAPI {

    @GET("comercio")
    Call<List<Comercio>> listarComercios();

    @GET("comercio/{id}")
    Call<Comercio> comercioPorID(@Path("id") Integer id);

    @POST("comercio")
    Call<Comercio> altaComercio(@Body Comercio comercio);

    @PUT("comercio")
    Call<Comercio> modificarComercio(@Body Comercio comercio);
}
