package com.revature;

import com.revature.controllers.*;
import com.revature.models.Reimbursement;
import com.revature.repos.ReimbursementDAO;
import com.revature.repos.ReimbursementDAOImpl;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;

public class App {

    private static Javalin app;
    private static Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        app = Javalin.create(); // for running tests through postman

        app = Javalin.create((config)->{ // mario run
            config.addStaticFiles("C:\\Revature\\Project1\\project-1-mario-_-eric\\FrontEnd",
                    Location.EXTERNAL);
        });

//        app = Javalin.create((config)->{ // eric run
//            config.addStaticFiles("C:\\Users\\flodev\\Desktop\\ProjectOne\\project-1-mario-_-eric\\FrontEnd",
//                    Location.EXTERNAL);
//        });

        configure(new ReimbursementController(),
                new LoginController());

        app.start(7002);
        log.info("started the app!");


    }

    public static void configure(Controller... controllers){

        for (Controller c: controllers){
            c.addRoutes(app);
        }
    }
}

