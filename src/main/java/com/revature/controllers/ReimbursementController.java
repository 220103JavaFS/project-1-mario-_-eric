package com.revature.controllers;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.models.User.UserRole;
import com.revature.service.ReimbursementService;
import com.revature.utils.SessionUtil;
import io.javalin.Javalin;

import io.javalin.http.Handler;

import java.util.List;

public class ReimbursementController implements Controller{

    private ReimbursementService reimbursementService = new ReimbursementService();

    private final Handler viewAllReimbursements = ctx -> {
        User u = SessionUtil.UserValidate(ctx, UserRole.Manager);
        if (u != null) {
            List<Reimbursement> rem_list = reimbursementService.getAllReimbursements();

            ctx.json(rem_list);
            ctx.status(200);
        }
    };

    @Override
    public void addRoutes(Javalin app) {
        app.post("/reimbursements", viewAllReimbursements);
    }
}
