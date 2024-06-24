package com.mysecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mysecurity.entity.Laptop;
import com.mysecurity.repository.LaptopRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LaptopService {
    @Autowired
    private LaptopRepository repo;

    public void addLaptop(Laptop laptop) {
        repo.save(laptop);
    }

    public List<Laptop> getAllLaptops() {
        return repo.findAll();
    }

    public Optional<Laptop> getLaptopById(int id) {
        return repo.findById(id);
    }
}