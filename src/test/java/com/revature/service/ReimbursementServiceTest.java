package com.revature.service;

import com.revature.models.Reimbursement;
import com.revature.repos.ReimbursementDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReimbursementServiceTest {

    private ReimbursementService testInstance;

    @Mock
    private ReimbursementDAO mockedDAO;

    private Reimbursement testReim;

    @BeforeEach
    public void setReimbursement(){
        testReim = new Reimbursement();
        testReim.setAmount(300);
        testReim.setDateSubmitted(new Timestamp(System.currentTimeMillis()));
        testReim.setStatusId(1);
        testReim.setAuthorId(2);
        testReim.setDescription("Test Reimbursement");
        testReim.setTypeId(1);
        MockitoAnnotations.openMocks(this);
        testInstance = new ReimbursementService(mockedDAO);
        // Returns test_reim when getting by Id 1
        Mockito.when(testInstance.getById(1)).thenReturn(testReim);
        // Returns true when user tries to save reim
        Mockito.when(testInstance.saveReimbursement(testReim)).thenReturn(true);
        // New list to test getAll
        List<Reimbursement> reim_list = new ArrayList<>();
        reim_list.add(testReim);
        // Returns List with 1 reimbursement when calling getAllReims
        Mockito.when(testInstance.getAllReimbursements()).thenReturn(reim_list);
    }

    @Test
    void getAllReimbursements() {
        assertFalse(testInstance.getAllReimbursements().isEmpty());
    }

    @Test
    void getByUserId() {
        assertNotNull(testInstance.getByUserId(1));
    }

    @Test
    void getByNegativeUserId() {
        assertNull(testInstance.getByUserId(-1));
    }

    @Test
    void getByWrongReimbursementId() {
        assertNull(testInstance.getById(999));
    }

    @Test
    void getByNegativeReimbursementId() {
        assertNull(testInstance.getById(-1));
    }

    @Test
    void getByReimbursementId() {
        assertNotNull(testInstance.getById(1));
    }

    @Test
    void saveReimbursement() {
        assertTrue(testInstance.saveReimbursement(testReim));
    }

    @Test
    void saveNegativeAmountReimbursement(){
        testReim.setAmount(-1.0);
        assertFalse(testInstance.saveReimbursement(testReim));
    }

    @Test
    void saveNegativeUserReimbursement(){
        testReim.setAuthorId(-1);
        assertFalse(testInstance.saveReimbursement(testReim));
    }
}