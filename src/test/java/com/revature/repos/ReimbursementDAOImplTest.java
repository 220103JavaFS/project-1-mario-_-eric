package com.revature.repos;

import com.revature.models.Reimbursement;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class ReimbursementDAOImplTest {

    private static ReimbursementDAO reimbursementDAO = new ReimbursementDAOImpl();
    private static Reimbursement reim = new Reimbursement(
            1000.0,
            new Timestamp(System.currentTimeMillis()),
            null,
            "to delete",
            "",
            1,
            2,
            1,
            1

    );


    @Test
    @Order(1)
    void save() {

        assertTrue(reimbursementDAO.save(reim));
    }

    // COME BACK TO THIS, KEEPS DELETING ALL RECORDS IN REIMBURSEMENT
//    @Test
//    @Order(7)
//    void deleteByTimestamp() {
//        assertTrue(reimbursementDAO.deleteByTimestamp(reim.getDateSubmitted()));
//        assertNull(reimbursementDAO.getByTimestamp(reim.getDateSubmitted()));
//    }

    @Test
    @Order(2)
    void getAllByUserId() {
        assertFalse(reimbursementDAO.getAllByUserId(reim.getAuthorId()).isEmpty());

    }

    @Test
    @Order(3)
    void getAllByStatus() {
        assertFalse(reimbursementDAO.getAllByStatus(reim.getStatusId()).isEmpty());
    }

    @Test
    @Order(4)
    void updateStatusIdTest() {
        reim.setStatusId(3);
        reim.setResolverId(2);
        reim.setDateResolved(new Timestamp(System.currentTimeMillis()));
        assertTrue(reimbursementDAO.update(reim));
    }

    @Test
    @Order(5)
    void getAllReimbursements(){

        assertFalse(reimbursementDAO.getAll().isEmpty());
    }
}