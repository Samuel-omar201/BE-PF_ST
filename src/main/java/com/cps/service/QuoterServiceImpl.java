package com.cps.service;

import com.cps.data.bo.Country;
import com.cps.data.bo.Region;
import com.cps.data.dto.PackageDTO;
import com.cps.data.repository.CountryRepository;
import com.cps.data.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuoterServiceImpl implements QuoterService {

    @Value("${config.const1}")
    private Float const1;

    @Value("${config.const2}")
    private Float const2;

    private final RegionRepository regionRepository;
    private final CountryRepository countryRepository;

    public QuoterServiceImpl(RegionRepository regionRepository, CountryRepository countryRepository) {
        this.regionRepository = regionRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public Float getQuotation(PackageDTO dto) {
        Optional<Country> country = countryRepository.findById(dto.getCountryId());
        Optional<Region> region = regionRepository.findById(country.get().getIdRegion());
        dto.setRate(region.get().getRate());

        Float response = (dto.getWeight() * dto.getRate()) + this.const1 * dto.getHeight() * dto.getLength() * dto.getWidth() * this.const2 * dto.getWeight();

        return response;
    }

}
