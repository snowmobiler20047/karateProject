package com.idahokenpo.kenposchedule.data;

import lombok.Data;
import org.bson.types.ObjectId;

/**
 *
 * @author Korey
 */
@Data
public abstract class Person
{
   protected String personId;
   private String prefix;
   private String firstName;
   private String middleName;
   private String lastName;
   private String phoneNumber;
   private String address;
   private String email;
   private BeltRank rank;
   
   public String getNickname()
   {
       return prefix + "." + lastName;
   }
}
