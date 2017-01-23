package com.idahokenpo.kenposchedule.data;

/**
 *
 * @author Korey
 */
public enum LessonCost
{
    NORMAL_PRIVATE(27.00d, PaymentFrequency.WEEKLY),
    PREMIUM_PRIVATE(32.00d, PaymentFrequency.WEEKLY),
    GROUP_ONLY(50.00d, PaymentFrequency.MONTHLY);
    
    private final double cost;
    private final PaymentFrequency frequency;
    
    private LessonCost(double cost, PaymentFrequency frequency)
    {
        this.cost = cost;
        this.frequency = frequency;
    }

    public double getCost()
    {
        return cost;
    }

    public PaymentFrequency getFrequency()
    {
        return frequency;
    }
}
