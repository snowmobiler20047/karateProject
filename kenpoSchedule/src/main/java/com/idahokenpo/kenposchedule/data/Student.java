package com.idahokenpo.kenposchedule.data;

import org.bson.types.ObjectId;

/**
 *
 * @author Korey
 */
public class Student extends Person
{ 
   private String parentName;
   
   public Student()
   {
       super.personId = new ObjectId().toHexString();
   }
   public Student(String id)
   {
       super.personId = id;
   }
}
