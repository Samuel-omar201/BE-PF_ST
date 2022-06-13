package com.cps.data.repository;

import com.cps.data.bo.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {

}
