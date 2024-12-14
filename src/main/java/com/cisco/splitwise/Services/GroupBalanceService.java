package com.cisco.splitwise.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cisco.splitwise.Models.Expense;
import com.cisco.splitwise.Models.User;
import com.cisco.splitwise.strategy.BalanceCalculationTemplate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GroupBalanceService extends BalanceCalculationTemplate {
    private String groupId;
    public GroupBalanceService(ExpenseService expenseService, String groupId) {
        super(expenseService);
        this.groupId = groupId;
    }

    public List<Expense> getExpenses() {
        return expenseService.getExpenseByGroupId(groupId);
    }

    public Map<User,Double> showBalance(){
            return balances;
    }
}
