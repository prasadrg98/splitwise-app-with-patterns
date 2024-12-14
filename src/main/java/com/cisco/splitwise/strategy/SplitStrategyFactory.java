package com.cisco.splitwise.strategy;

import com.cisco.splitwise.Enums.SplitType;
import com.cisco.splitwise.Services.UserService;

//For Factory Pattern, extensible on new strategy types
public class SplitStrategyFactory {
    private UserService userService;

    public SplitStrategyFactory(UserService userService) {
        this.userService = userService;
    }

    public SplitStrategy getSplitStrategy(SplitType splitType) {
        switch (splitType) {
            case EQUAL:
                return new EqualSplitStrategy();
            case PERCENT:
                return new PercentageSplitStrategy(this.userService);
            default:
                return new EqualSplitStrategy();
        }
    }
}
