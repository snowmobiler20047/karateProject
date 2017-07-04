/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idahokenpo.kenposchedule.billing;

import com.idahokenpo.kenposchedule.data.Instructor;
import com.idahokenpo.kenposchedule.data.Lesson;
import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Korey
 */
public class AccountTest
{
    private Payment payment;
    private Account account;
    private LocalDate testDate = LocalDate.of(2017, 7, 3);
    
    public AccountTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
	payment = new Payment(100, LocalDate.of(2017, 7, 3), LocalTime.MIN);
	account = new Account("test");
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of applyPayment method, of class Account.
     */
    @Test
    public void testApplyPayment()
    {
	System.out.println("applyPayment");

	account.applyPayment(payment);

	assertEquals(100d, account.getCurrentBalance(testDate).getBalance(), .00001);
    }

    /**
     * Test of editPayment method, of class Account.
     */
//    @Test
    public void testEditPayment()
    {
	System.out.println("editPayment");
	Payment payment = null;
	Account instance = null;
	instance.editPayment(payment);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of applyLessonCost method, of class Account.
     */
//    @Test
    public void testApplyLessonCost()
    {
	System.out.println("applyLessonCost");
	LocalDate date = null;
	Lesson lesson = null;
	Instructor instructor = null;
	Account instance = null;
	instance.applyLessonCost(date, lesson, instructor);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of getAccountStatus method, of class Account.
     */
//    @Test
    public void testGetAccountStatus()
    {
	System.out.println("getAccountStatus");
	LocalDate date = null;
	Account instance = null;
	AccountStatus expResult = null;
	AccountStatus result = instance.getAccountStatus(date);
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentBalance method, of class Account.
     */
//    @Test
    public void testGetCurrentBalance()
    {
	System.out.println("getCurrentBalance");
	LocalDate date = null;
	Account instance = null;
	Balance expResult = null;
	Balance result = instance.getCurrentBalance(date);
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }   
}
