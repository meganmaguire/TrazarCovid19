package ar.com.degedev.trazar_covid.backend.service;

import ar.com.degedev.trazar_covid.backend.util.ExpressionChecker;
import ar.com.degedev.trazar_covid.frontend.service_subscriber.ServiceSubscriber;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Service {
    private ExpressionChecker expressionChecker;
    private ServiceSubscriber serviceSubscriber;

    public abstract void searchComercios() throws Exception;
}
