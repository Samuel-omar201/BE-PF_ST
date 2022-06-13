package com.cps.data.repository;

import com.cps.data.bo.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}
