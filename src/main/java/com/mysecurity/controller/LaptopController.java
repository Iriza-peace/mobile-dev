package com.mysecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.mysecurity.entity.Laptop;
import com.mysecurity.entity.UserData;
import com.mysecurity.service.JwtService;
import com.mysecurity.service.LaptopService;
import com.mysecurity.service.UserDataService;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/laptops")
public class LaptopController {
    @Autowired
    private LaptopService laptopService;
    @Autowired
    private UserDataService userServices;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> addLaptop(@RequestBody Laptop laptop, HttpServletRequest request) {
        try {
            String authHeader = request.getHeader("Authorization");
            String token = null;
            String username = null;
            if (authHeader != null && authHeader.startsWith("Bearer")) {
                token = authHeader.substring(7);
                username = jwtService.extractUsername(token);
            }
            UserData info = userServices.loadCurrentUser(username);
            laptop.setCreatedBy(info);
            laptopService.addLaptop(laptop);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public List<Laptop> getAllLaptops() {
        return laptopService.getAllLaptops();
    }

    @GetMapping("/{id}")
    public Optional<Laptop> getLaptopById(@PathVariable int id) {
        return laptopService.getLaptopById(id);
    }
}