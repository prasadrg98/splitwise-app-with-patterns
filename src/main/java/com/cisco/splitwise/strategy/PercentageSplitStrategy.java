package com.cisco.splitwise.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cisco.splitwise.Models.Share;
import com.cisco.splitwise.Models.User;
import com.cisco.splitwise.Services.UserService;

public class PercentageSplitStrategy implements SplitStrategy {
    private UserService userService;
    public PercentageSplitStrategy(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<Share> splitShares(int amount, List<User> users, Map<String, Map<String, String>> params) {
        
        Map<String, String> percentageMap =  params.get("percentage");
        int totalPercentage = percentageMap.values().stream().mapToInt(Integer::parseInt).sum();

        if(totalPercentage != 100) {
            throw new RuntimeException("Total percentage is not 100");
        }

        List<Share> shares = new ArrayList<>();
        percentageMap.entrySet().stream().forEach(entry -> {
            User user = userService.getUserById(entry.getKey());
            // System.out.println("Key: " + entry.getKey() + " Value: " + entry.getValue() + " parseInt = " + Integer.parseInt(entry.getValue()) / 100);
            double percentage = Integer.parseInt(entry.getValue());
            int amt = (int) (amount * (percentage / 100.0));
            shares.add(new Share(user, amt));
        });

        return shares;
    }
    
    
}
