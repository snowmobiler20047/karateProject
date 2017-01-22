/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idahokenpo.kenposchedule.data;

import lombok.Data;

/**
 *
 * @author Korey
 */
@Data
public abstract class Person
{
   private int id;
   private String firstName;
   private String middleName;
   private String lastName;
   private String phoneNumber;
   private String address;
   private String email;
   private BeltRank rank;        
}
