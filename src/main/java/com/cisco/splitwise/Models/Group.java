package com.cisco.splitwise.Models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Group {
    private String groupId;
    private String name;
    private List<User> members;
    private List<Expense> expenses;
}
