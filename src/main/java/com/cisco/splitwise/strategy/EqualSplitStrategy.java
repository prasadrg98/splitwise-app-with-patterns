package com.cisco.splitwise.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cisco.splitwise.Models.Share;
import com.cisco.splitwise.Models.User;

public class EqualSplitStrategy implements SplitStrategy {

    @Override
    public List<Share> splitShares(int amount, List<User> users, Map<String, Map<String, String>> params) {
        int shareAmount = amount / users.size();
        List<Share> shares = new ArrayList<>();

        for (User user : users) {
            shares.add(new Share(user, shareAmount));
        }
        return shares;
    }

}
