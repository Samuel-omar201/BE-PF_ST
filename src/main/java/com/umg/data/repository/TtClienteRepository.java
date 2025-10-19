package com.umg.data.repository;

import com.umg.data.bo.TtCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TtClienteRepository extends JpaRepository<TtCliente, Integer> {
}
