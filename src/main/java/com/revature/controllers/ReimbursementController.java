package com.revature.controllers;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementDTO;
import com.revature.models.User;
import com.revature.models.User.UserRole;
import com.revature.service.ReimbursementService;
import com.revature.utils.SessionUtil;
import io.javalin.Javalin;

import io.javalin.http.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.List;

public class ReimbursementController implements Controller{

    private static Logger log = LoggerFactory.getLogger(ReimbursementController.class);

    private ReimbursementService reimbursementService = new ReimbursementService();

    private final Handler viewAllReimbursements = ctx -> {
        User u = SessionUtil.UserValidate(ctx, UserRole.Employee);
        if (u != null ) {
            if(u.getUserRole()==UserRole.Manager) {
                List<Reimbursement> rem_list = reimbursementService.getAllReimbursements();

                ctx.json(rem_list);
                ctx.status(200);
                log.info("VIEWING_ALL_REIMBURSEMENTS : " + u.getUsername() + "\n" + ctx.path());
            }else{
                List<Reimbursement> emp_list = reimbursementService.getByUserId(u.getId());
                ctx.json(emp_list);
                ctx.status(200);
                log.info("VIEWING_REIMBURSEMENTS : " + u.getUsername() + "\n" + ctx.path());
            }
        }
    };

    private final Handler viewByStatusId = ctx -> {
        User u = SessionUtil.UserValidate(ctx, UserRole.Manager);
        if (u != null) {
            System.out.println(ctx.body());
            String idString = ctx.pathParam("statusId");
            int statusId = Integer.parseInt(idString);

            List<Reimbursement> rem_list = reimbursementService.getReimbursementsByStatusId(statusId);
            ctx.json(rem_list);
            ctx.status(200);
            log.info("VIEWING_REIMBURSEMENTS_STATUS : " + u.getUsername() + "\n" + ctx.path());
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

            reim.setReceipt(dto.receipt);

            reim.setDateSubmitted(new Timestamp(System.currentTimeMillis()));
            reim.setAuthorId(u.getId());
            reim.setStatusId(1);

            if(reimbursementService.saveReimbursement(reim)){
                ctx.status(201);
                log.info("CREATED_REIMBURSEMENT : " + u.getUsername() + "\n" + reim +  "\n" + ctx.path());
            }else{
                ctx.status(400);
                log.info("ERROR_CREATING_REIMBURSEMENT : " + u.getUsername() + "\n" + reim +  "\n" + ctx.path());
            }
        }
    };

    private final Handler updateTicket = ctx -> {
        User u = SessionUtil.UserValidate(ctx, UserRole.Manager);
        if (u != null){
            System.out.println(ctx.body());

            ReimbursementDTO dto = ctx.bodyAsClass(ReimbursementDTO.class);

            Reimbursement reim = reimbursementService.getById(dto.reimId);

            if (reim != null) {
                reim.setStatusId(dto.statusId);
                reim.setDateResolved(new Timestamp(System.currentTimeMillis()));
                reim.setResolverId(u.getId());

                if (reimbursementService.updateReimbursement(reim)) {
                    ctx.status(200);
                    ctx.json(reim);
                    log.info("UPDATED_REIMBURSEMENT : " + u.getUsername() + "\n" + reim +  "\n" + ctx.path());
                } else {
                    ctx.status(400);
                    log.info("ERROR_UPDATING_REIMBURSEMENT : " + u.getUsername() + "\n" + reim +  "\n" + ctx.path());
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
