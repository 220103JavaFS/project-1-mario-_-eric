package com.revature.repos;

import com.revature.models.Reimbursement;
import com.revature.utils.ConnectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

            StringBuffer sql = new StringBuffer("DELETE FROM ers_reimbursement WHERE reimb_id = " + id + " CASCADE;");
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

        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM ers_reimbursement;";

            Statement statement = conn.createStatement();

            ResultSet result = statement.executeQuery(sql);

            List<Reimbursement> list = new ArrayList<>();

            while(result.next()){
                Reimbursement reimb = new Reimbursement();
                reimb.setId(result.getInt("reimb_id"));
                reimb.setAmount(result.getDouble("reimb_amount"));
                reimb.setDateSubmitted(result.getString("reimb_submitted"));
                reimb.setDateResolved(result.getString("reimb_resolved"));
                reimb.setDescription(result.getString("reimb-description"));
                reimb.setAuthorId(result.getInt("reimb_author"));

                list.add(reimb);
            }

            return list;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public Reimbursement get(int id) {
        return null;
    }
}
