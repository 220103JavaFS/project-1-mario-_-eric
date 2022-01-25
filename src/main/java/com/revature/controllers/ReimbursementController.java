package com.revature.controllers;

import com.revature.models.Reimbursement;
import com.revature.service.ReimbursementService;
import io.javalin.Javalin;

import io.javalin.http.Handler;

import java.util.List;

public class ReimbursementController implements Controller{

    private ReimbursementService reimbursementService = new ReimbursementService();

    private final Handler viewAllReimbursements = ctx -> {
        List<Reimbursement> rem_list = reimbursementService.getAllReimbursements();

        ctx.json(rem_list);
        ctx.status(200);
    };

    @Override
    public void addRoutes(Javalin app) {
        app.post("/reimbursements", viewAllReimbursements);
    }
}
