package com.revature.service;

import com.revature.models.Reimbursement;
import com.revature.repos.ReimbursementDAO;
import com.revature.repos.ReimbursementDAOImpl;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ReimbursementService {

    private ReimbursementDAO reimbursementDAO = new ReimbursementDAOImpl();

    public List<Reimbursement> getAllReimbursements() { return reimbursementDAO.getAll(); }

    public List<Reimbursement> getByUserId(int userId){
        // Makes sure user ID is valid
        if (userId > 0){
            return reimbursementDAO.getAllByUserId(userId);
        }
        return null;
    }

    public Reimbursement getByReimbursementId(int id){
        // Makes sure user ID is valid
        if (id > 0){
            return reimbursementDAO.get(id);
        }
        return null;
    }

    public boolean saveReimbursement(@NotNull Reimbursement r) {
        // Validates amount
        if (r.getAmount() == 0) {
            return false;
        }
        // Validates user
        if (r.getAuthorId() == 0) {
            return false;
        }

        return reimbursementDAO.save(r);
    }

}
