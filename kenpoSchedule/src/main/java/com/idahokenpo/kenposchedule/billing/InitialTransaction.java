package com.idahokenpo.kenposchedule.billing;

import java.time.LocalDate;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class InitialTransaction implements Transaction
{
    private final String id;
    private final double amount = 0d;
    private final TransactionType type = TransactionType.INITIAL;
    private final LocalDate date = LocalDate.MIN;
    
    public InitialTransaction()
    {
	this(new ObjectId().toHexString());
    }
    
    public InitialTransaction(String id)
    {
	this.id = id;
    }
    
    @Override
    public String getId()
    {
	return id;
    }

    @Override
    public double getAmount()
    {
	return amount;
    }

    @Override
    public TransactionType getTransactionType()
    {
	return type;
    }

    @Override
    public LocalDate getDate()
    {
	return date;
    }
}
