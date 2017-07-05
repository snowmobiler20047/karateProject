package com.idahokenpo.kenposchedule.billing;

import java.time.LocalDate;

/**
 *
 * @author Korey
 */
public interface Transaction
{    
   public String getId();
   public double getAmount();
   public TransactionType getTransactionType();
   public LocalDate getDate();
}
