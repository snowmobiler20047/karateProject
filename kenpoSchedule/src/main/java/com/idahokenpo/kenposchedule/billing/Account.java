package com.idahokenpo.kenposchedule.billing;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.idahokenpo.kenposchedule.data.Instructor;
import com.idahokenpo.kenposchedule.data.Lesson;
import java.time.LocalDate;
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
    private NavigableMap<LocalDate, Balance> balanceHistory;
    private NavigableMap<LocalDate, Set<Payment>> paymentHistory;
    private boolean active;

    public Account()
    {
        this.accountId = new ObjectId().toHexString();
        this.balanceHistory = Maps.newTreeMap();
        this.paymentHistory = Maps.newTreeMap();
    }
    
    public void applyPayment(Payment payment)
    {
        Set<Payment> payments = paymentHistory.get(payment.getDate());
        if (payments == null)
        {
            payments = Sets.newHashSet();
        }
        payments.add(payment);
        Balance prevBalance = balanceHistory.floorEntry(payment.getDate()).getValue();
        
        Balance balance = new Balance(prevBalance.getBalance() + payment.getAmount(), "Payment", payment.getPaymentId());
         
        balanceHistory.put(payment.getDate(), balance);
    }
    
    public void applyLessonCost(LocalDate date, Lesson lesson, Instructor instructor)
    {
        Balance prevBalance = balanceHistory.floorEntry(date).getValue();
        
        Balance balance = new Balance(prevBalance.getBalance() - lesson.calculateCost(instructor), "Lesson", lesson.getLessonId());
         
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
