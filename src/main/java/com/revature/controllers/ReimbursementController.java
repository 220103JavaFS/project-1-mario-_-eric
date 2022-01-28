package com.revature.controllers;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementDTO;
import com.revature.models.User;
import com.revature.models.User.UserRole;
import com.revature.service.ReimbursementService;
import com.revature.utils.SessionUtil;
import io.javalin.Javalin;

import io.javalin.http.Handler;

import java.sql.Timestamp;
import java.util.List;

public class ReimbursementController implements Controller{

    private ReimbursementService reimbursementService = new ReimbursementService();

    private final Handler viewAllReimbursements = ctx -> {
        User u = SessionUtil.UserValidate(ctx, UserRole.Employee);
        if (u != null ) {
            if(u.getUserRole()==UserRole.Manager) {
                List<Reimbursement> rem_list = reimbursementService.getAllReimbursements();

                ctx.json(rem_list);
                ctx.status(200);
            }else{
                List<Reimbursement> emp_list = reimbursementService.getByUserId(u.getId());
                ctx.json(emp_list);
                ctx.status(200);
            }
        }
    };

    private final Handler viewByStatusId = ctx -> {
        User u = SessionUtil.UserValidate(ctx, UserRole.Manager);
        if (u != null) {
            String idString = ctx.pathParam("statusId");
            int statusId = Integer.parseInt(idString);

            List<Reimbursement> rem_list = reimbursementService.getReimbursementsByStatusId(statusId);
            ctx.json(rem_list);
            ctx.status(200);
        }
    };

    private final Handler createTicket = ctx -> {
        User u = SessionUtil.UserValidate(ctx, UserRole.Employee);
        if (u != null){
            System.out.println(ctx.body());
            ReimbursementDTO dto = ctx.bodyAsClass(ReimbursementDTO.class);
            Reimbursement reim = new Reimbursement();
            reim.setAmount(dto.amount);
            reim.setDescription(dto.description);
            reim.setTypeId(dto.typeId);
            reim.setDateSubmitted(new Timestamp(System.currentTimeMillis()));
            reim.setAuthorId(u.getId());
            reim.setStatusId(1);

            if(reimbursementService.saveReimbursement(reim)){
                ctx.status(201);
            }else{
                ctx.status(400);
            }
        }
    };

    private final Handler updateTicket = ctx -> {
        User u = SessionUtil.UserValidate(ctx, UserRole.Manager);
        if (u != null){
            ReimbursementDTO dto = ctx.bodyAsClass(ReimbursementDTO.class);

            Reimbursement reim = reimbursementService.getById(dto.reimId);
            if (reim != null) {
                reim.setStatusId(dto.statusId);
                reim.setDateResolved(new Timestamp(System.currentTimeMillis()));
                reim.setResolverId(u.getId());

                if (reimbursementService.updateReimbursement(reim)) {
                    ctx.status(200);
                    ctx.json(reim);
                } else {
                    ctx.status(400);
                }
            }

        }
    };

    @Override
    public void addRoutes(Javalin app) {
        app.get("/reimbursements", viewAllReimbursements);
        app.get("/reimbursements/{statusId}", viewByStatusId);
        app.post("/reimbursements", createTicket);
        app.put("/reimbursements/", updateTicket);
    }
}
