package com.idahokenpo.kenposchedule.billing;

import java.time.LocalDate;
import lombok.Data;

/**
 *
 * @author Korey
 */
@Data
public class IncomeReport
{
    private final double income;
    private final double lessonCost;
    private final LocalDate startDate;
    private final LocalDate endDate;
}
