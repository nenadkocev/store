package com.example.emtlabsjdbc.service.impl;

import com.example.emtlabsjdbc.model.Manufacturer;
import com.example.emtlabsjdbc.repository.ManufacturerRepository;
import com.example.emtlabsjdbc.service.ManufacturerService;
import org.springframework.stereotype.Service;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public void save(Manufacturer manufacturer) {
        manufacturerRepository.save(manufacturer);
    }
}
