package com.revature.controllers;

import com.revature.models.Encryption;
import com.revature.models.User;
import com.revature.models.UserDTO;
import com.revature.service.LoginService;
import com.revature.service.UserService;
import io.javalin.Javalin;
import io.javalin.http.Handler;

public class LoginController implements Controller{

    private LoginService loginService = new LoginService();
    private UserService userService = new UserService();

    private final Handler validateAccount = ctx -> {
        // Check if session is not created
        if (ctx.req.getSession(false) == null){
            // Grabs information from request
            UserDTO dto = ctx.bodyAsClass(UserDTO.class);
            // Creates new user with request info
            dto.password = Encryption.stringToMD5(dto.password);
            User u = new User(dto.username, dto.password);

            if (loginService.validateAccount(u)) {
                User db_user = userService.getUserByUsername(dto.username);
                ctx.req.getSession().setAttribute("user", db_user);
                ctx.json(db_user);
                ctx.status(200);
            } else {
                ctx.status(400);
            }

        } else {
            ctx.status(400);
        }

    };

    private final Handler invalidateSession = ctx -> {
        // Checks if session is created
        if (ctx.req.getSession(false) != null) {
            ctx.req.getSession().invalidate();
            ctx.status(200);
        } else {
            ctx.status(400);
        }
    };

    @Override
    public void addRoutes(Javalin app) {
     app.post("/login", validateAccount);
     app.post("/logout", invalidateSession);
    }
}
