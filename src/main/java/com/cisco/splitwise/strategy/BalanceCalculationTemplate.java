package com.cisco.splitwise.strategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cisco.splitwise.Models.Expense;
import com.cisco.splitwise.Models.User;
import com.cisco.splitwise.Services.ExpenseService;

// Added this for template pattern where only one step get expenses is different
public abstract class BalanceCalculationTemplate {
    protected Map<User, Double> balances;
    protected ExpenseService expenseService;

    public BalanceCalculationTemplate(ExpenseService expenseService) {
        this.balances = new HashMap<>();
        this.expenseService = expenseService;
    }

    public abstract List<Expense> getExpenses();

    public void calculateBalances() {
        // Template method
        // Step 1: Fetch all expenses
        List<Expense> expenses = getExpenses();
        
        // Step 2: Calculate balances
        for (Expense expense : expenses) {
            calculateShares(expense);
            calculatePaidBy(expense);
        }
    }

    public void calculateShares(Expense expense) {
        expense.getShares().forEach(share -> {
            User user = share.getUser();
            double amount = share.getAmount();

            if (balances.containsKey(user)) {
                balances.put(user, balances.get(user) + amount);
            } else {
                balances.put(user, amount);
            }
        });
    }

    public void calculatePaidBy(Expense expense) {
        User paidBy = expense.getPaidBy();
        balances.put(paidBy, balances.getOrDefault(paidBy, 0.0) - expense.getAmount());
    }
}
