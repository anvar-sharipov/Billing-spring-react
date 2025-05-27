package com.WEAK.telekom.services;

import com.WEAK.telekom.models.UserTable;
import com.WEAK.telekom.repositories.UserTableRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserTableService {

    private final UserTableRepository userTableRepository;

    public UserTableService(UserTableRepository userTableRepository) {
        this.userTableRepository = userTableRepository;
    }

    @Transactional
    public void bulkInsertNumbers() {
//        int batchSize = 2000;
//        List<UserTable> users = new ArrayList<>(batchSize);
//
//        for (int i = 20000; i <= 99999; i++) {
//            UserTable user = new UserTable();
//            user.setNumber(String.valueOf(i));
//            // Другие поля по желанию заполняй
//
//            users.add(user);
//
//            if (users.size() == batchSize) {
//                userTableRepository.saveAll(users);
//                users.clear();
//            }
//        }
//
//        if (!users.isEmpty()) {
//            userTableRepository.saveAll(users);
//        }
    }
}
