package com.mysecurity.repository;

import com.mysecurity.entity.Banking;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface BankingRepository extends JpaRepository<Banking, Integer>{

    Optional<Banking> findById(int id);

    Banking save(Banking banking);


}
