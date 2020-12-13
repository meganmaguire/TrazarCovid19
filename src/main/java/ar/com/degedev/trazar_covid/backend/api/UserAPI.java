package ar.com.degedev.trazar_covid.backend.api;

import ar.com.degedev.trazar_covid.frontend.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserAPI {
    @POST("login")
    Call<Void> login(@Body User user);
}
