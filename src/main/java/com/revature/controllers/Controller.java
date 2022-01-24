package com.revature.controllers;


import io.javalin.Javalin;

public abstract class Controller {

    public abstract void addRoutes(Javalin app);

}