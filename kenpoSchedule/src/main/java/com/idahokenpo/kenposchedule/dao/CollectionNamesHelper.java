package com.idahokenpo.kenposchedule.dao;

/**
 *
 * @author Korey
 */
public enum CollectionNamesHelper
{
    STUDENTS("students", "personId"),
    ACCOUNTS("accounts", "accountId"),
    INSTRUCTORS("instructors", "personId"),
    WEEKLY_SCHEDULE("weekly-schedule", "weeklyScheduleId");
    
    private final String collectionName;
    private final String keyId;

    private CollectionNamesHelper(String collectionName, String keyId)
    {
        this.collectionName = collectionName;
        this.keyId = keyId;
    }

    public String getCollectionName()
    {
        return collectionName;
    }

    public String getKeyId()
    {
        return keyId;
    }
}
