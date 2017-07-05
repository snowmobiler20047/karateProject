package com.idahokenpo.kenposchedule.billing;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Data;
import org.bson.types.ObjectId;

/**
 *
 * @author Korey
 */
@Data
public class Payment implements Transaction
{
    private String paymentId;
    private double amount;
    private final TransactionType type = TransactionType.PAYMENT;
    private LocalDate date;
    
    public Payment(double amount, LocalDate date, LocalTime time)
    {
	this(new ObjectId().toHexString(), amount, date, time);
    }
    
    public Payment(String paymentId, double amount, LocalDate date, LocalTime time)
    {
        this.paymentId = paymentId;
        this.amount = amount;
        this.date = date;
    }

    @Override
    public String getId()
    {
	return paymentId;
    }
    
    @Override
    public TransactionType getTransactionType()
    {
	return type;
    }
}
