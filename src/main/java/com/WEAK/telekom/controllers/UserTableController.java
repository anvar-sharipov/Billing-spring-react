package com.WEAK.telekom.controllers;

import com.WEAK.telekom.services.UserTableService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserTableController {

    private final UserTableService userTableService;

    public UserTableController(UserTableService userTableService) {
        this.userTableService = userTableService;
    }

    @GetMapping("/bulk-insert-users")
    public String bulkInsert() {
        userTableService.bulkInsertNumbers();
        return "Bulk insert started!";
    }
}
