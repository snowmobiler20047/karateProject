package com.idahokenpo.kenposchedule.billing;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.idahokenpo.kenposchedule.data.LessonLink;
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
    private double balance;
    private NavigableMap<LocalDate, Set<Payment>> paymentHistory;
    private Set<LessonLink> lessonLinks;

    public Account()
    {
        this.accountId = new ObjectId().toHexString();
        this.balance = 0;
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
        balance += payment.getAmount();
    }
    
    public void applyLessonCost(double cost)
    {
        balance -= cost;
    }
    
    public AccountStatus getAccountStatus()
    {
        if(balance < 0)
            return AccountStatus.PAYMENT_DUE;
        
        return AccountStatus.GOOD;
    }
}
