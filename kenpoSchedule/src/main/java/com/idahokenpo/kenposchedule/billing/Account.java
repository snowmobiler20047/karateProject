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
import java.util.SortedMap;
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
    private NavigableMap<LocalDate, Balance> balanceHistory;
    private NavigableMap<LocalDate, Set<Payment>> paymentHistory;
    private boolean active;
    private LessonLink lessonLink;

    public Account(String name)
    {
        this.accountId = new ObjectId().toHexString();
        this.balanceHistory = Maps.newTreeMap();
        this.paymentHistory = Maps.newTreeMap();
	this.active = true;
	this.name = name;
    }
    
    public void applyPayment(Payment payment)
    {
        Set<Payment> payments = paymentHistory.get(payment.getDate());
        if (payments == null)
        {
            payments = Sets.newHashSet();
        }
        payments.add(payment);
	paymentHistory.put(payment.getDate(), payments);
	
	Balance prevBalance;
	if (balanceHistory.lowerEntry(payment.getDate()) == null)
	{
	    balanceHistory.put(LocalDate.MIN, new Balance(0d, 0d, "Initial", "NA")); // initilize it to 0
	}
	
        prevBalance = balanceHistory.floorEntry(payment.getDate()).getValue();
        
        Balance balance = new Balance(prevBalance.getBalance() + payment.getAmount(), payment.getAmount(), "Payment", payment.getPaymentId());
         
        balanceHistory.put(payment.getDate(), balance);
    }
    
    public void editPayment(Payment payment)
    {
	Set<Payment> payments = paymentHistory.get(payment.getDate());
        if (payments == null)
        {
            throw new IllegalStateException("Payment does not exist with paymentId: " + payment.getPaymentId());
        }
        Payment uneditedPayment = payments.stream().filter(p -> p.getPaymentId().equals(payment.getPaymentId())).findFirst().get();
	payments.remove(uneditedPayment);
	payments.add(payment);
	
	Balance prevBalance = balanceHistory.floorEntry(payment.getDate()).getValue();
        
        Balance balance = new Balance(prevBalance.getBalance() + payment.getAmount(), payment.getAmount(), "Payment", payment.getPaymentId());
         
        balanceHistory.put(payment.getDate(), balance);
    }
    
    private void recalculateBalances(LocalDate date)
    {
	Balance prevBalance = balanceHistory.lowerEntry(date).getValue();
	
	SortedMap<LocalDate, Balance> filteredBalanceHistory = Maps.newTreeMap(balanceHistory.tailMap(date));
	
	for (Map.Entry<LocalDate, Balance> entry : filteredBalanceHistory.entrySet())
	{
	    Balance balance = entry.getValue();
	    double updatedBalanceAmount = prevBalance.getBalance() + balance.getAmount();
	    Balance updatedBalance = new Balance(updatedBalanceAmount, balance.getAmount(), balance.getType(), balance.getId());
	    prevBalance = updatedBalance;
	    
	    balanceHistory.put(entry.getKey(), updatedBalance);
	}
    }
    
    public void applyLessonCost(LocalDate date, Lesson lesson, Instructor instructor)
    {
        Balance prevBalance = balanceHistory.floorEntry(date).getValue();
        double lessonCost = lesson.calculateCost(instructor, lessonLink);
        Balance balance = new Balance(prevBalance.getBalance() - lessonCost, -lessonCost, "Lesson", lesson.getLessonId());
         
        balanceHistory.put(date, balance);
    }
    
    public AccountStatus getAccountStatus(LocalDate date)
    {
        double balance = balanceHistory.floorEntry(date).getValue().getBalance();
        if(balance < 0)
            return AccountStatus.PAYMENT_DUE;
        
        return AccountStatus.GOOD;
    }

    public Balance getCurrentBalance(LocalDate date)
    {
        return balanceHistory.floorEntry(date).getValue();
    }
}
