package com.revature;

import com.revature.controllers.*;
import com.revature.models.Reimbursement;
import com.revature.repos.ReimbursementDAO;
import com.revature.repos.ReimbursementDAOImpl;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;

public class App {

    private static Javalin app;
    private static Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        app = Javalin.create();

        configure(new ReimbursementController());

        app.start();
        log.info("started the app!");

        Reimbursement reim = new Reimbursement(
                1000.0,
                new Timestamp(System.currentTimeMillis()),
                null,
                "to delete",
                1,
                2,
                1,
                1

        );

    }

    public static void configure(Controller... controllers){

        for (Controller c: controllers){
            c.addRoutes(app);
        }
    }
}

