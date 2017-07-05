package com.idahokenpo.kenposchedule.billing;

import java.time.LocalDate;
import org.bson.types.ObjectId;

public class InitialTransaction implements Transaction
{
    public final String id;
    
    public InitialTransaction()
    {
	this.id = new ObjectId().toHexString();
    }
    
    @Override
    public String getId()
    {
	return id;
    }

    @Override
    public double getAmount()
    {
	return 0d;
    }

    @Override
    public TransactionType getTransactionType()
    {
	return TransactionType.INITIAL;
    }

    @Override
    public LocalDate getDate()
    {
	return LocalDate.MIN;
    }
}
