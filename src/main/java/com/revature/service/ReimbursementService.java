package com.revature.service;

import com.revature.models.Reimbursement;
import com.revature.repos.ReimbursementDAO;
import com.revature.repos.ReimbursementDAOImpl;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ReimbursementService {

    private ReimbursementDAO reimbursementDAO;

    public ReimbursementService(ReimbursementDAO reimbursementDAO) {
        this.reimbursementDAO = reimbursementDAO;
    }

    public ReimbursementService() {
        this.reimbursementDAO = new ReimbursementDAOImpl();
    }

    public List<Reimbursement> getAllReimbursements() {
        return reimbursementDAO.getAll();
    }

    public List<Reimbursement> getByUserId(int userId){
        // Makes sure user ID is valid
        if (userId > 0){
            return reimbursementDAO.getAllByUserId(userId);
        }
        return null;
    }

    public List<Reimbursement> getReimbursementsByStatusId(int statusId){
        if (statusId > 0){
            return reimbursementDAO.getAllByStatus(statusId);
        }
        return new ArrayList<>();
    }

    public Reimbursement getById(int id){
        if (id > 0){
            return reimbursementDAO.get(id);
        }
        return null;
    }

    public List<Reimbursement> getByAuthorId(int authorId){
        // Makes sure user ID is valid
        if (authorId > 0){
            return reimbursementDAO.getAllByUserId(authorId);
        }
        return null;
    }

    public boolean updateReimbursement(Reimbursement r){
        return reimbursementDAO.update(r);
    }

    public boolean saveReimbursement(@NotNull Reimbursement r) {
        // Validates amount
        if (r.getAmount() < 1) {
            return false;
        }
        // Validates user
        if (r.getAuthorId() < 1) {
            return false;
        }

        return reimbursementDAO.save(r);
    }

}
