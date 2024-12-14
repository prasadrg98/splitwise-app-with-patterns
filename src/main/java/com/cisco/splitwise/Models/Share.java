package com.cisco.splitwise.Models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Share {
    private User user;
    private double amount;

    public Share(User user, double amount) {
        this.user = user;
        this.amount = amount;
    }
}
