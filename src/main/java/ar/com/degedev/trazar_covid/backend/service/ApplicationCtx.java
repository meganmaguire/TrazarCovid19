package ar.com.degedev.trazar_covid.backend.service;

import lombok.Getter;
import lombok.Setter;

public class ApplicationCtx {

    @Getter
    @Setter
    private String authToken = "";

    @Getter
    private ApiFactory APIs;

    private static ApplicationCtx instance;

    private ApplicationCtx() {
        this.APIs = new ApiFactory();
    }

    public static ApplicationCtx getInstance() {
        if (ApplicationCtx.instance == null) {
            ApplicationCtx.instance = new ApplicationCtx();
        }
        return ApplicationCtx.instance;
    }
}
