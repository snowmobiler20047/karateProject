package com.idahokenpo.kenposchedule.billing;

import java.time.LocalDate;
import lombok.Data;

@Data
public class LessonTransaction implements Transaction
{
    private final String lessonId;
    private double amount;
    private final TransactionType type = TransactionType.LESSON;
    private LocalDate date;
    
    public LessonTransaction(String lessonId, double amount, LocalDate date)
    {
	this.lessonId = lessonId;
	this.amount = amount;
	this.date = date;
    }

    @Override
    public String getId()
    {
	return lessonId;
    }

    @Override
    public double getAmount()
    {
	return -amount;
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
