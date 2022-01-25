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

    @Test
    @Order(2)
    void deleteByTimestamp() {
        assertTrue(reimbursementDAO.deleteByTimestamp(reim.getDateSubmitted()));
        assertNull(reimbursementDAO.getByTimestamp(reim.getDateSubmitted()));
    }
}