package com.cisco.splitwise.Models;

import java.util.List;
import java.util.Map;

import com.cisco.splitwise.Enums.SplitType;
import com.cisco.splitwise.Services.UserService;
import com.cisco.splitwise.strategy.SplitStrategy;
import com.cisco.splitwise.strategy.SplitStrategyFactory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Expense {
    private String expenseId;
    private String groupId;
    private User paidBy;
    private double amount;
    private String description;
    private SplitType splitType;
    private List<Share> shares;

    public Expense(String expenseId, String groupId, User paidBy, double amount, String description) {
        this.expenseId = expenseId;
        this.groupId = groupId;
        this.paidBy = paidBy;
        this.amount = amount;
        this.description = description;
    }

    public void calculateShares(UserService userService, List<User> participants, SplitType splitType,
            Map<String, Map<String, String>> params) {
        SplitStrategy strategy = new SplitStrategyFactory(userService).getSplitStrategy(splitType);
        this.shares = strategy.splitShares((int) this.amount, participants, params);
        this.splitType = splitType;
    }
}
