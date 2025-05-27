package com.WEAK.telekom.repositories;


import com.WEAK.telekom.models.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTableRepository extends JpaRepository<UserTable, Long> {
}
