package com.idahokenpo.kenposchedule.billing;

import lombok.Data;

/**
 *
 * @author Korey
 */
@Data
public class Balance
{
    private final double balance;
    private final double amount;
    private final String type;
    private final String id;

    public Balance(double balance, double amount, String type, String id)
    {
        this.balance = balance;
	this.amount = amount;
        this.type = type;
        this.id = id;
    }
}
