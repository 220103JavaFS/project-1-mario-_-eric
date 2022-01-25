package com.revature.controllers;

import com.revature.models.User;
import com.revature.models.UserDTO;
import com.revature.service.LoginService;
import com.revature.service.ResponseType;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginController implements Controller {

    private LoginService loginService = new LoginService();
    private final Logger log = LoggerFactory.getLogger(LoginController.class);

    private final Handler login = ctx ->{

        UserDTO userDTO = ctx.bodyAsClass(UserDTO.class);
        User u = new User(userDTO.username, userDTO.password);

        ResponseType result = loginService.login(u);

        if (result == ResponseType.SUCCESS){

            User user = loginService.getLoginUsername(u.getUsername());

            ctx.req.getSession().setAttribute("user", user);

            ctx.json(user);

            ctx.status(200);
        } else {

            ctx.status(400);
        }

    };


    @Override
    public void addRoutes(Javalin app) {
        app.post("/login", login);
    }
}
