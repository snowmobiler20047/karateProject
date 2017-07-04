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
public class Payment
{
    private String paymentId;
    private double amount;
    private LocalDate date;
    private LocalTime time;
    
    public Payment(double amount, LocalDate date, LocalTime time)
    {
	this(new ObjectId().toHexString(), amount, date, time);
    }
    
    public Payment(String paymentId, double amount, LocalDate date, LocalTime time)
    {
        this.paymentId = paymentId;
        this.amount = amount;
        this.date = date;
        this.time = time;
    }
}
