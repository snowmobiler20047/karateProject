/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    private final String type;
    private final String id;

    public Balance(double balance, String type, String id)
    {
        this.balance = balance;
        this.type = type;
        this.id = id;
    }
    
}
