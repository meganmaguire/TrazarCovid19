package ar.com.degedev.trazar_covid.api;

import ar.com.degedev.trazar_covid.model.Cliente;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface ClienteAPI {

    @GET("cliente")
    Call<List<Cliente>> listarClientes();

    @GET("cliente/{id}")
    Call<Cliente> clientePorID(@Path("id") Integer id);
}
