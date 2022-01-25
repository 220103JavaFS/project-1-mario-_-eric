package com.revature.repos;

import com.revature.models.Reimbursement;
import com.revature.utils.ConnectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ReimbursementDAOImpl implements ReimbursementDAO {

    private final Logger log = LoggerFactory.getLogger(ReimbursementDAOImpl.class);

    @Override
    public List<Reimbursement> getAllByUserId(int userId) {
        return null;
    }

    @Override
    public List<Reimbursement> getAllByStatus(int statusId) {
        return null;
    }

    @Override
    public boolean save(Reimbursement r) {
        return false;
    }

    @Override
    public boolean update(Reimbursement r) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        try(Connection conn = ConnectionUtil.getConnection()) {

            StringBuffer sql = new StringBuffer("DELETE FROM customer_orders WHERE order_id = " + id + ";");
            Statement statement = conn.createStatement();

            statement.executeUpdate(sql.toString());

            return true;

        } catch (SQLException e) {
            log.warn("Error while deleting reimbursement id : " + id);
            log.error(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Reimbursement> getAll() {
        return null;
    }

    @Override
    public Reimbursement get(int id) {
        return null;
    }
}
