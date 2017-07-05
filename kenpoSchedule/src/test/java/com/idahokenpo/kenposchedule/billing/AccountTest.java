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
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

	assertEquals(100d, account.getCurrentBalance(testDate), .00001);
    }

    /**
     * Test of editPayment method, of class Account.
     */
    @Test
    public void testEditPayment()
    {
	account.applyPayment(payment);
	
	assertEquals(100d, account.getCurrentBalance(testDate), .00001);
	
	Payment editedPayment = new Payment(payment.getId(), 50d, testDate, LocalTime.MIN);
	
	account.editPayment(editedPayment);
	
	assertEquals("Edited amount", 50d, account.getCurrentBalance(testDate), .00001);
	assertTrue("Transactions size", account.getTransactionHistory().get(testDate).size() == 1);
    }

    /**
     * Test of applyLessonCost method, of class Account.
     */
    @Test
    public void testApplyLessonCost()
    {
	System.out.println("applyLessonCost");
	
	Lesson lesson = mock(Lesson.class);
	when(lesson.getLessonId()).thenReturn("lessonId");
	when(lesson.calculateCost(Mockito.any(Instructor.class), Mockito.any())).thenReturn(27d);
	
	Instructor instructor = mock(Instructor.class);
	
	account.applyLessonCost(testDate, lesson, instructor);
	
	assertEquals("Balance", -27d, account.getBalance(), .00001);
	assertTrue("Transactions size", account.getTransactionHistory().get(testDate).size() == 1);
    }

    /**
     * Test of getAccountStatus method, of class Account.
     */
    @Test
    public void testGetAccountStatus()
    {
	Lesson lesson = mock(Lesson.class);
	when(lesson.getLessonId()).thenReturn("lessonId");
	when(lesson.calculateCost(Mockito.any(Instructor.class), Mockito.any())).thenReturn(27d);
	
	Instructor instructor = mock(Instructor.class);
	
	account.applyLessonCost(testDate, lesson, instructor);

	AccountStatus result = account.getAccountStatus(testDate);
	assertEquals(AccountStatus.PAYMENT_DUE, result);
	
	account.applyPayment(payment);
	
	result = account.getAccountStatus(testDate);
	assertEquals(AccountStatus.GOOD, result);
    }  
}
