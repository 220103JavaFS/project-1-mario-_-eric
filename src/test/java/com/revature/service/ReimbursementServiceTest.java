package com.revature.service;

import com.revature.models.Reimbursement;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReimbursementServiceTest {

    public static Reimbursement reimb;


    @BeforeAll
    public static void setReimbursement(){

        reimb = new Reimbursement();
    }

    @Test
    void getAllReimbursements() {
    }

    @Test
    void getByUserId() {
    }

    @Test
    void getByReimbursementId() {
    }

    @Test
    void saveReimbursement() {
    }

    @AfterAll
    public static void clearReimb(){
        reimb = null;
    }
}