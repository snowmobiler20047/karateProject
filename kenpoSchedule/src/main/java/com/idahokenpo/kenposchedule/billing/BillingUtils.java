package com.idahokenpo.kenposchedule.billing;

import com.idahokenpo.kenposchedule.dao.AccountDao;
import com.idahokenpo.kenposchedule.data.Instructor;
import com.idahokenpo.kenposchedule.data.Lesson;
import java.time.LocalDate;
import java.util.Map;

/**
 *
 * @author Korey
 */
public class BillingUtils
{
    private final AccountDao dao = new AccountDao();
    
    public void chargeLesson(LocalDate date, Lesson lesson, Instructor instructor)
    {
        Account account = dao.get(lesson.getAccountId());
        
        account.applyLessonCost(date, lesson, instructor);
    }
    
    public String runReport()
    {
        LocalDate date = LocalDate.now();
        StringBuilder sb = new StringBuilder();
        for (Account account : dao.getAll())
        {
            sb.append(account.getAccountId()).append("\t");
            sb.append(account.getAccountStatus(date)).append("\t");
            sb.append(account.getCurrentBalance(date)).append("\t");
        }
         
        return sb.toString();
    }
//    public String runReportForAccount(String accountId, LocalDate date)
//    {
//        Account account = dao.get(accountId);
//        
//        StringBuilder sb = new StringBuilder();
//        sb.append(accountId).append("\t").append(account.getAccountStatus(date)).append("\n");
//        for (Map.Entry<LocalDate, Balance> balanceEntry : account.getBalanceHistory().entrySet())
//        {
//            sb.append(balanceEntry.getKey()).append("\t");
//            Balance balance = balanceEntry.getValue();
//            sb.append(balance.getBalance())
//                    .append("\t")
//                    .append(balance.getType())
//                    .append("\t")
//                    .append(balance.getId())
//                    .append("\n");
//        }
//        
//        return sb.toString();
//    }
}
