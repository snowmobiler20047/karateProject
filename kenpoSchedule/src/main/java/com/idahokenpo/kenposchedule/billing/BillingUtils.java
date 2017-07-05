package com.idahokenpo.kenposchedule.billing;

import com.idahokenpo.kenposchedule.dao.AccountDao;
import com.idahokenpo.kenposchedule.data.Instructor;
import com.idahokenpo.kenposchedule.data.Lesson;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        StringBuilder sb = new StringBuilder();
        for (Account account : dao.getAll())
        {
            sb.append(account.getAccountId()).append("\t");
	    sb.append(account.getName()).append("\t");
            sb.append(account.getAccountStatus()).append("\t");
            sb.append(account.getBalance()).append("\t");
        }
         
        return sb.toString();
    }
    
    public String runReportForAccount(String accountId)
    {
        Account account = dao.get(accountId);
        
        StringBuilder sb = new StringBuilder();
        sb.append(accountId).append("\t").append(account.getAccountStatus()).append("\n");
	for (Map.Entry<LocalDate, Set<Transaction>> entry : account.getTransactionHistory().entrySet())
	{
	    sb.append(entry.getKey()).append("\n\t");
            for (Transaction transaction : entry.getValue())
	    {
		sb.append(transaction.getId())
                    .append("\t")
                    .append(transaction.getTransactionType())
                    .append("\t")
                    .append(transaction.getAmount())
                    .append("\n");
	    }
            
	}
	sb.append("Balance: ").append(account.getBalance());
	
        return sb.toString();
    }
    
    public IncomeReport runIncomeReport(LocalDate startDate, LocalDate endDate)
    {
	List<Account> accounts = dao.getAll();
	double totalIncome = 0d;
	double lessonCost = 0d;
	
	for (Account account : accounts)
	{
	    for (Map.Entry<LocalDate, Set<Transaction>> entry : account.getTransactionHistory().subMap(startDate, true, endDate, true).entrySet())
	    {
		for (Transaction transaction : entry.getValue())
		{
		    switch (transaction.getTransactionType())
		    {
			case PAYMENT:
			    totalIncome += transaction.getAmount();
			    break;
			case LESSON:
			    lessonCost += transaction.getAmount();
			    break;
		    }		    
		}
	    }
	}
	
	return new IncomeReport(totalIncome, lessonCost, startDate, endDate);
    }
}
