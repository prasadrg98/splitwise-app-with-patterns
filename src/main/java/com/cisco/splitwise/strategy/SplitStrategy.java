package com.cisco.splitwise.strategy;

import java.util.List;
import java.util.Map;

import com.cisco.splitwise.Models.Share;
import com.cisco.splitwise.Models.User;

public interface SplitStrategy {
    List<Share> splitShares(int amount, List<User> users, Map<String, Map<String, String>> params); // percentage -> (User Id -> value)
}