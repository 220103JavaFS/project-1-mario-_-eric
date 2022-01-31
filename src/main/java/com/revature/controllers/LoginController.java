package com.revature.controllers;

import com.revature.models.Encryption;
import com.revature.models.User;
import com.revature.models.UserDTO;
import com.revature.service.LoginService;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginController implements Controller{

    private LoginService loginService = new LoginService();

    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    private final Handler validateAccount = ctx -> {
        System.out.println(ctx.body());
        // Check if session is not created
        if (ctx.req.getSession(false) == null){
            // Grabs information from request
            UserDTO dto = ctx.bodyAsClass(UserDTO.class);
            // Creates new user with request info
            dto.password = Encryption.stringToMD5(dto.password);
            User u = new User(dto.username, dto.password);

            User dbUser = loginService.validateAccount(u.getUsername(), u.getPassword());
            if (dbUser != null) {
                ctx.req.getSession().setAttribute("user", dbUser);
                ctx.json(dbUser);
                ctx.status(200);
                log.info("USER_LOGGED_IN : " + u.getUsername() +  "\n"+ ctx.path());
            } else {
                ctx.status(400);
                log.info("ERROR_LOGGIN_IN : database user not found " + "\n"+ ctx.path());
            }

        } else {
            ctx.status(400);
            log.info("ERROR_LOGGIN_IN : User already logged in " + "\n" + ctx.path());
        }

    };

    private final Handler invalidateSession = ctx -> {
        // Checks if session is created
        if (ctx.req.getSession(false) != null) {
            ctx.req.getSession().invalidate();
            ctx.status(200);
            log.info("USER_LOGGED_OUT " + "\n" + ctx.path());
        } else {
            ctx.status(400);
            log.info("ERROR_LOGGIN_OUT " + "\n" + ctx.path());
        }
    };

    @Override
    public void addRoutes(Javalin app) {
     app.post("/login", validateAccount);
     app.post("/logout", invalidateSession);
    }
}
