package com.revature.repos;

import com.revature.models.Reimbursement;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public interface ReimbursementDAO extends DAO<Reimbursement> {
    // Methods that we only use in the ReimbursementDAOImpl
    List<Reimbursement> getAllByUserId(int userId);
    List<Reimbursement> getAllByStatus(int statusId);

    // Get/Delete by Timestamp
    Reimbursement getByTimestamp(Timestamp t);
    boolean deleteByTimestamp(Timestamp t);

    // These are methods that pretty much all DAO objects have
    @Override
    boolean save(Reimbursement r);
    @Override
    boolean update(Reimbursement r);
    @Override
    boolean delete(int id);
    @Override
    List<Reimbursement> getAll();
    @Override
    Reimbursement get(int id);
}
