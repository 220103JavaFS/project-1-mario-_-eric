package com.revature.service;

import com.revature.models.Reimbursement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class ReimbursementServiceTest {

    private ReimbursementService reimbursementService;

    private Reimbursement reim;

    @BeforeEach
    public void setReimbursement(){
        reimbursementService = new ReimbursementService();

        reim = new Reimbursement();
        reim.setAmount(300);
        reim.setDateSubmitted(new Timestamp(System.currentTimeMillis()));
        reim.setStatusId(1);
        reim.setAuthorId(2);
        reim.setDescription("Test Reimbursement");
        reim.setTypeId(1);

    }

    @Test
    void getAllReimbursements() {
        assertFalse(reimbursementService.getAllReimbursements().isEmpty());
    }

    @Test
    void getByUserId() {
        assertNotNull(reimbursementService.getByUserId(1));
    }

    @Test
    void getByNegativeUserId() {
        assertNull(reimbursementService.getByUserId(-1));
    }

    @Test
    void getByWrongReimbursementId() {
        assertNull(reimbursementService.getById(999));
    }

    @Test
    void getByNegativeReimbursementId() {
        assertNull(reimbursementService.getById(-1));
    }

    @Test
    void getByReimbursementId() {
        assertNotNull(reimbursementService.getById(38));
    }

    @Test
    void saveReimbursement() {
        assertTrue(reimbursementService.saveReimbursement(reim));
    }

    @Test
    void saveNegativeAmountReimbursement(){
        reim.setAmount(-1.0);
        assertFalse(reimbursementService.saveReimbursement(reim));
    }

    @Test
    void saveNegativeUserReimbursement(){
        reim.setAuthorId(-1);
        assertFalse(reimbursementService.saveReimbursement(reim));
    }

// COME BACK TO THIS, KEEPS DELETING ALL RECORDS IN REIMBURSEMENT
//    @Test
//    @Order(7)
//    void deleteByTimestamp() {}
}