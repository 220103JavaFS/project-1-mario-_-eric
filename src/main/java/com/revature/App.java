package com.revature;

import com.revature.controllers.*;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    private static Javalin app;
    private static Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        app = Javalin.create();

        configure();

        app.start();
        log.info("started the app!");
    }

    public static void configure(Controller... controllers){

        for (Controller c: controllers){
            c.addRoutes(app);
        }
    }
}

