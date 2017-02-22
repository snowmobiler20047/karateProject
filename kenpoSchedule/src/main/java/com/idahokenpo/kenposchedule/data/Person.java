package com.idahokenpo.kenposchedule.data;

import lombok.Data;

/**
 *
 * @author Korey
 */
@Data
public abstract class Person
{
   protected String personId;
   protected String prefix;
   protected String firstName;
   protected String middleName;
   protected String lastName;
   protected String phoneNumber;
   protected String address;
   protected String email;
   protected boolean active;
   protected BeltRank rank;
   
   public String getNickname()
   {
       return prefix + "." + lastName;
   }
}
