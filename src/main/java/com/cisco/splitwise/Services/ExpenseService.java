package com.cisco.splitwise.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.cisco.splitwise.Models.Expense;

public class ExpenseService {
    List<Expense> expenses;

    public ExpenseService() {
        this.expenses = new ArrayList<>();
    }

    public void addExpense(Expense expense) {
        this.expenses.add(expense);
    }

    public Expense getExpense(String expenseId) {
        for (Expense expense : expenses) {
            if (expense.getExpenseId().equals(expenseId)) {
                return expense;
            }
        }
        return null;
    }

    public List<Expense> getAllExpenses() {
        return expenses;
    }

    public List<Expense> getExpenseByGroupId(String groupId) {
        return expenses.stream().filter(expense -> {
            return expense.getGroupId().equals(groupId);
        }).collect(Collectors.toList());
    }
}
