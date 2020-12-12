package ar.com.degedev.trazar_covid.backend.service;

import ar.com.degedev.trazar_covid.backend.interfaz.ComercioOperator;
import ar.com.degedev.trazar_covid.backend.persistance.ComercioOperatorImpl;
import ar.com.degedev.trazar_covid.frontend.model.Comercio;
import ar.com.degedev.trazar_covid.frontend.service_subscriber.ComercioServiceSubscriber;
import javafx.concurrent.Task;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ComercioService extends Service{
    private ComercioOperator operator;

    public ComercioService(){
        this.operator = new ComercioOperatorImpl(this);
    }

    public void searchComercios() throws Exception
    {
        //CustomAlert customAlert = this.getServiceSubscriber().showProcessIsWorking("Wait a moment while the process is done.");

        Task<Void> task = new Task<Void>()
        {
            @Override
            protected Void call() throws Exception
            {
                List<Comercio> comercios = operator.findAll();

                //getServiceSubscriber().closeProcessIsWorking(customAlert);
                if(comercios != null)
                {
                    ((ComercioServiceSubscriber)getServiceSubscriber()).showComercios(comercios);
                }
                else
                {
                    getServiceSubscriber().showNoResult("No users registered");
                }

                return null;
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(task);
    }

}
