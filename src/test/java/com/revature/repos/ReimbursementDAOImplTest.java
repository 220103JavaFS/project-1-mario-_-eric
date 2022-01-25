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
            1,
            1000,
            new Timestamp(System.currentTimeMillis()),
            null,
            "to delete",
            1,
            2,
            1,
            1

    );


    @Test
    @Order(2)
    void delete() {

        assertTrue(reimbursementDAO.delete(reim.getId()));
        assertNull(reimbursementDAO.get(reim.getId()));
    }

    @Test
    @Order(1)
    void save() {

        assertTrue(reimbursementDAO.save(reim));
    }
}