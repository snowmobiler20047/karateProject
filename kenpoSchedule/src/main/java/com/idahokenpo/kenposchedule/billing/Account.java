package com.idahokenpo.kenposchedule.billing;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.idahokenpo.kenposchedule.data.Instructor;
import com.idahokenpo.kenposchedule.data.Lesson;
import com.idahokenpo.kenposchedule.data.LessonLink;
import java.time.LocalDate;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import lombok.Data;
import org.bson.types.ObjectId;

/**
 *
 * @author Korey
 */
@Data
public class Account
{
    private String accountId;
    private String name;
    private double balance;
    private NavigableMap<LocalDate, Set<Transaction>> transactionHistory;
    private boolean active;
    private LessonLink lessonLink;

    public Account(String name)
    {
	this.accountId = new ObjectId().toHexString();
	this.balance = 0d;
	this.transactionHistory = Maps.newTreeMap();
	Set<Transaction> transactions = Sets.newHashSet();
	transactions.add(new InitialTransaction());
	transactionHistory.put(LocalDate.MIN, transactions);
	this.active = true;
	this.name = name;
    }

    public void applyPayment(Payment payment)
    {
	Set<Transaction> transactions = getTransactions(payment.getDate());

	transactions.add(payment);

	balance += payment.getAmount();
    }

    private Set<Transaction> getTransactions(LocalDate date)
    {
	Set<Transaction> transactions = transactionHistory.get(date);
	if (transactions == null)
	{
	    transactions = Sets.newHashSet();
	    transactionHistory.put(date, transactions);
	}
	return transactions;
    }

    public void editPayment(Payment payment)
    {
	Set<Transaction> transactions = transactionHistory.get(payment.getDate());
	if (transactions == null)
	{
	    throw new IllegalStateException("No transactions on: " + payment.getDate());
	}
	Payment uneditedPayment = (Payment) transactions.stream().filter(t -> t.getId().equals(payment.getId()) && t.getTransactionType().equals(TransactionType.PAYMENT)).findFirst().get();
	
	if (uneditedPayment == null)
	{
	    throw new IllegalStateException("Payment does not exist with paymentId: " + payment.getPaymentId());
	}
	
	balance -= uneditedPayment.getAmount();
	balance += payment.getAmount();	
		
	uneditedPayment = payment; 
    }

    public void recalculateBalance()
    {
	double updatedBalanceAmount = 0d;

	for (Map.Entry<LocalDate, Set<Transaction>> entry : transactionHistory.entrySet())
	{
	    Set<Transaction> transactions = entry.getValue();
	    updatedBalanceAmount = transactions.stream().map((transaction) -> transaction.getAmount()).reduce(updatedBalanceAmount, (accumulator, _item) -> accumulator + _item);
	}
	this.balance = updatedBalanceAmount;
    }
    
    public void applyLessonCost(LocalDate date, Lesson lesson, Instructor instructor)
    {
	double lessonCost = lesson.calculateCost(instructor, lessonLink);
	
	LessonTransaction lessonTran = new LessonTransaction(lesson.getLessonId(), lessonCost, date);
	Set<Transaction> transactions = getTransactions(date);
	transactions.add(lessonTran);
	
	balance += lessonTran.getAmount();
    }

    public AccountStatus getAccountStatus(LocalDate date)
    {
	if (balance < 0)
	{
	    return AccountStatus.PAYMENT_DUE;
	}

	return AccountStatus.GOOD;
    }
}
