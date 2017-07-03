package com.idahokenpo.kenposchedule.data;

import org.bson.types.ObjectId;

/**
 *
 * @author Korey
 */
public class Student extends Person
{  
   public Student(String prefix, String firstName, String lastName)
   {
       super.personId = new ObjectId().toHexString();
       super.prefix = prefix;
       super.firstName = firstName;
       super.lastName = lastName;
       super.active = true;
   }
   
   public Student(String id)
   {
       super.personId = id;
   }
}
