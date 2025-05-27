package com.WEAK.telekom.repositories;

import com.WEAK.telekom.models.Etrap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtrapRepository extends JpaRepository<Etrap, Long> {
}
