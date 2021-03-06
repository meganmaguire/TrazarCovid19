package ar.com.degedev.trazar_covid.backend.service;

import ar.com.degedev.trazar_covid.backend.api.ClienteAPI;
import ar.com.degedev.trazar_covid.backend.api.ComercioAPI;
import ar.com.degedev.trazar_covid.backend.api.RegistroAPI;
import ar.com.degedev.trazar_covid.backend.api.UserAPI;
import ar.com.degedev.trazar_covid.frontend.model.User;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import lombok.val;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ApiFactory {
    private static final String BASE_URL = "http://localhost:8080/";

    private final Retrofit retrofit;

    private ClienteAPI clienteAPI;
    private ComercioAPI comercioAPI;
    private RegistroAPI registroAPI;
    private UserAPI userAPI;

    public ApiFactory() {
        val gson = new GsonBuilder().setPrettyPrinting()
                .registerTypeAdapter(
                        LocalDateTime.class,
                        (JsonSerializer<LocalDateTime>) (localDateTime, type, ctx) ->
                                new JsonPrimitive(localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)))
                .registerTypeAdapter(
                        LocalDateTime.class,
                        (JsonDeserializer<LocalDateTime>) (jsonElement, type, ctx) ->
                                LocalDateTime.parse(jsonElement.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                )
                .setLenient().create();
        val httpClient = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    val original = chain.request();
                    val request = original.newBuilder()
                            .header("Authorization", ApplicationCtx.getInstance().getAuthToken())
                            .header("Content-Type", "application/json")
                            .method(original.method(), original.body())
                            .build();
                    return chain.proceed(request);
                }).build();
        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .build();
    }

    public ClienteAPI getClienteAPI() {
        if (this.clienteAPI == null) {
            this.clienteAPI = this.retrofit.create(ClienteAPI.class);
        }
        return this.clienteAPI;
    }

    public ComercioAPI getComercioAPI() {
        if (this.comercioAPI == null) {
            this.comercioAPI = this.retrofit.create(ComercioAPI.class);
        }
        return this.comercioAPI;
    }

    public RegistroAPI getRegistroAPI() {
        if (this.registroAPI == null) {
            this.registroAPI = this.retrofit.create(RegistroAPI.class);
        }
        return this.registroAPI;
    }

    public void login(User user) throws IOException {
        if (this.userAPI == null) {
            this.userAPI = this.retrofit.create(UserAPI.class);
        }
        val authToken = this.userAPI
                .login(user).execute()
                .headers().get("Authorization");
        if (authToken == null) {
            throw new IOException("Authorization header from " + BASE_URL + " was null");
        }
        ApplicationCtx.getInstance().setAuthToken(authToken);
    }
}
