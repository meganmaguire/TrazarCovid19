package ar.com.degedev.trazar_covid.backend.persistance;

import ar.com.degedev.trazar_covid.backend.api.ComercioAPI;
import ar.com.degedev.trazar_covid.backend.interfaz.ComercioOperator;
import ar.com.degedev.trazar_covid.backend.service.ComercioService;
import ar.com.degedev.trazar_covid.frontend.Main;
import ar.com.degedev.trazar_covid.frontend.model.Comercio;
import ar.com.degedev.trazar_covid.frontend.service_subscriber.ComercioServiceSubscriber;
import javafx.application.Platform;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ComercioOperatorImpl implements ComercioOperator {
    private final static int REQUEST_CONNECT_TIMEOUT_TOLERANCE = 20;
    private final static int REQUEST_READ_TIMEOUT_TOLERANCE = 5;
    private final static int REQUEST_WRITE_TIMEOUT_TOLERANCE = 5;

    public final static String ID = "id";
    public final static String RESOURCE = "/comercio";
    public final static String SINGLE_RESOURCE = RESOURCE + "/{" + ID + "}";

    static ComercioOperatorImpl operator;

    private ComercioService comercioService;
    private ComercioAPI comercioAPI;
    private OkHttpClient okHttpClient;
    private Retrofit retrofit;

    public ComercioOperatorImpl(ComercioService comercioService) {
        this.comercioService = comercioService;

        // HttpClient and Rest Client can be injected for more decoupling
        this.okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(REQUEST_CONNECT_TIMEOUT_TOLERANCE, TimeUnit.SECONDS)
                .readTimeout(REQUEST_READ_TIMEOUT_TOLERANCE, TimeUnit.SECONDS)
                .writeTimeout(REQUEST_WRITE_TIMEOUT_TOLERANCE, TimeUnit.SECONDS).build();

        this.retrofit = new Retrofit.Builder().baseUrl(Main.API_HOSTNAME).client(this.okHttpClient)
                .addConverterFactory(GsonConverterFactory.create()).build();

        this.comercioAPI = this.retrofit.create(ComercioAPI.class);
    }

    @Override
    public Comercio insert(Comercio comercio) throws Exception
    {
        Comercio ret = null;
        Response<Comercio> response = null;
        try
        {
            response = this.comercioAPI.altaComercio(comercio).execute();
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }

        if(response != null && response.isSuccessful())
        {
            ret = response.body();
        }
        else
        {
            this.comercioService.getServiceSubscriber().showError("User registration error. Body:\n"+response.errorBody().toString());
        }

        return ret;
    }

    @Override
    public List<Comercio> insertMany(List<Comercio> list) throws Exception {
        return null;
    }

    @Override
    public Comercio update(Comercio comercio) throws Exception {
        return null;
    }

    @Override
    public List<Comercio> findAll() throws Exception
    {
        this.comercioAPI.listarComercios().enqueue
                (
                        new Callback<List<Comercio>>()
                        {
                            @Override
                            public void onResponse(Call<List<Comercio>> call, Response<List<Comercio>> response)
                            {
                                System.out.println("A");
                                if(response.isSuccessful())
                                {
                                    ((ComercioServiceSubscriber)comercioService.getServiceSubscriber()).showComercios(response.body());
                                }
                                else
                                {
                                    System.out.println("F");
                                    comercioService.getServiceSubscriber().showError("Cannot obtain a users list", response.errorBody().toString(), new Exception("Error response"));
                                }
                            };

                            @Override
                            public void onFailure(Call<List<Comercio>> call, Throwable throwable)
                            {
                                comercioService.getServiceSubscriber().showError("Find all user request fail", null, new Exception(throwable));
                            }
                        }
                );
        return null;
    }

    @Override
    public Comercio find(Integer id) throws Exception
    {
        this.comercioAPI.comercioPorID(id).enqueue(new Callback<Comercio>()
        {
            @Override
            public void onResponse(Call<Comercio> call, Response<Comercio> response)
            {
                if(response.isSuccessful())
                {
                    Platform.runLater(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            ((ComercioServiceSubscriber)comercioService.getServiceSubscriber()).showComercio(response.body());
                        }
                    });
                }
                else
                {
                    Platform.runLater(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            comercioService.getServiceSubscriber().showError("Cannot obtain comercio "+id, response.errorBody().toString(), new Exception("Error response"));
                        }
                    });
                }
            };

            @Override
            public void onFailure(Call<Comercio> call, Throwable throwable)
            {
                comercioService.getServiceSubscriber().showError("find comercio request fail", null, new Exception(throwable));
            };
        });
        return null;
    }

    @Override
    public Comercio delete(Integer id) throws Exception {
        return null;
    }
}
