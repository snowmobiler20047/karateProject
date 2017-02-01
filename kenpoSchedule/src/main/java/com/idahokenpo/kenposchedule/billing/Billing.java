package com.idahokenpo.kenposchedule.billing;

import com.idahokenpo.kenposchedule.data.Instructor;
import com.idahokenpo.kenposchedule.data.Lesson;
import java.time.LocalDate;
import java.util.Map;

/**
 *
 * @author Korey
 */
public class Billing
{
    private Map<String, Account> accountMap;
    
    public void chargeLesson(LocalDate date, Lesson lesson, Instructor instructor)
    {
        Account account = accountMap.get(lesson.getAccountId());
        
        account.applyLessonCost(date, lesson, instructor);
    }
    
    public String runReport()
    {
        LocalDate date = LocalDate.now();
        StringBuilder sb = new StringBuilder();
        for (Account account : accountMap.values())
        {
            sb.append(account.getAccountId()).append("\t");
            sb.append(account.getAccountStatus(date)).append("\t");
            sb.append(account.getCurrentBalance(date)).append("\t");
        }
         
        return sb.toString();
    }
    public String runReportForAccount(String accountId, LocalDate date)
    {
        Account account = accountMap.get(accountId);
        
        StringBuilder sb = new StringBuilder();
        sb.append(accountId).append("\t").append(account.getAccountStatus(date)).append("\n");
        for (Map.Entry<LocalDate, Balance> balanceEntry : account.getBalanceHistory().entrySet())
        {
            sb.append(balanceEntry.getKey()).append("\t");
            Balance balance = balanceEntry.getValue();
            sb.append(balance.getBalance())
                    .append("\t")
                    .append(balance.getType())
                    .append("\t")
                    .append(balance.getId())
                    .append("\n");
        }
        
        return sb.toString();
    }
}
