package com.cisco.splitwise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cisco.splitwise.Enums.SplitType;
import com.cisco.splitwise.Models.Expense;
import com.cisco.splitwise.Models.Group;
import com.cisco.splitwise.Models.Share;
import com.cisco.splitwise.Models.User;
import com.cisco.splitwise.Services.BalanceService;
import com.cisco.splitwise.Services.ExpenseService;
import com.cisco.splitwise.Services.GroupBalanceService;
import com.cisco.splitwise.Services.GroupService;
import com.cisco.splitwise.Services.UserService;
import com.cisco.splitwise.strategy.SplitStrategy;
import com.cisco.splitwise.strategy.SplitStrategyFactory;

@SpringBootApplication
public class SplitwiseApplication {

	public static void main(String[] args) {
        System.out.println("Hello World");
        
        User user1 = new User("1", "rg");
        User user2 = new User("2", "lokkesh");
        User user3 = new User("3", "prasad");

        UserService userService = new UserService();
        userService.addUser(user1);
        userService.addUser(user2);
        userService.addUser(user3);

        Group singaporeBills = new Group("G1", "Singapore", List.of(user1, user2), new ArrayList<>());
        GroupService groupService = new GroupService();
        groupService.addGroup(singaporeBills);

        
        int expenseAmount = 100;
        Expense waterBottleExpense = new Expense("Exp1", singaporeBills.getGroupId(), user1, expenseAmount, "Water Bottle");
        waterBottleExpense.calculateShares(userService, List.of(user1, user2, user3), SplitType.EQUAL, null);
        ExpenseService expenseService = new ExpenseService();
        expenseService.addExpense(waterBottleExpense);
        
        expenseAmount = 500;
        Expense foodExpense = new Expense("Exp2", "ad", user1, expenseAmount, "BF Expense");
        Map<String, Map<String, String>> params = new HashMap<>();
        params.put("percentage", Map.of(
            user1.getUserId(), "40",
            user2.getUserId(), "60"
        ));
        foodExpense.calculateShares(userService, List.of(user1, user2), SplitType.PERCENT, params);
        expenseService.addExpense(foodExpense);

        expenseService.getAllExpenses().forEach(System.out::println);

        BalanceService balanceService = new BalanceService(expenseService);
        balanceService.calculateBalances();
        System.out.println("Total Expense: " + balanceService.showBalance());

        GroupBalanceService groupBalanceService = new GroupBalanceService(expenseService, singaporeBills.getGroupId());
        groupBalanceService.calculateBalances();
        System.out.println("Group Balance: " + groupBalanceService.showBalance());

		SpringApplication.run(SplitwiseApplication.class, args);
	}

}
