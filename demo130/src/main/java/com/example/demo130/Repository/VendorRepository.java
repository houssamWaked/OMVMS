package com.example.demo130.Repository;


import com.example.demo130.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Integer> {


    Optional<Vendor> findByName(String name);


    Optional<Vendor> findByEmail(String email);
}
