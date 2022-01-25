package com.revature.controllers;


import io.javalin.Javalin;

public interface Controller {
    void addRoutes(Javalin app);
}