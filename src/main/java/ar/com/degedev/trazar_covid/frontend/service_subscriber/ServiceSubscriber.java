package ar.com.degedev.trazar_covid.frontend.service_subscriber;

import ar.com.degedev.trazar_covid.backend.service.Service;
import ar.com.degedev.trazar_covid.backend.util.CustomAlert;

public interface ServiceSubscriber
{
    Service getService(int location);

    int addService(Service service);
    /**
     * Called everytime that a use case is initiated.
     * IMPORTANT: DO NOT USE THREAD BLOCKING INSTRUCTIONS HERE.
     * OTHERWISE, THE USE CASE CANNOT CONTINUE.
     */
    CustomAlert showProcessIsWorking(String message);

    void closeProcessIsWorking(CustomAlert customAlert);

    /**
     * Called when a use case has succed.
     */
    void showSucces(String message);

    /**
     * Called when a use case has not succed.
     */
    void showError(String message);

    /**
     * Called when a use case has not succed.
     */
    void showError(String message, String description, Exception exception);

    /**
     * Called when a use case does not return an expected result.
     */
    void showNoResult(String message);
}
